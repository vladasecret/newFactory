package view;

import factory.Factory;
import main.FactoryConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements Runnable {
    private final StoragePanel storagePanel;
    private final WorkersPanel workersPanel;

    private final Factory factory;

    public MainFrame(FactoryConfig factoryConfig, Factory factory) {
        super("Factory");

        JTabbedPane tabbedPanel = new JTabbedPane();

        this.factory = factory;

        storagePanel = new StoragePanel(factoryConfig);
        workersPanel = new WorkersPanel(factoryConfig.getWorkersCount());
        TimeSettingsPanel timeSettingsPanel = new TimeSettingsPanel(factory);

        tabbedPanel.addTab("Storage", storagePanel);
        tabbedPanel.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPanel.addTab("Workers statuses", new JScrollPane(workersPanel));
        tabbedPanel.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPanel.addTab("Time settings", timeSettingsPanel);
        tabbedPanel.setMnemonicAt(2, KeyEvent.VK_3);

        add(tabbedPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    factory.stop();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                System.exit(0);
            }
        });

        setVisible(true);
        setMinimumSize(new Dimension(800, 450));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            storagePanel.getAccessoriesStorageBar().setValue(factory.getAccessoriesStorage().currentCountElements());
            storagePanel.getEnginesStorageBar().setValue(factory.getEnginesStorage().currentCountElements());
            storagePanel.getBodiesStorageBar().setValue(factory.getBodiesStorage().currentCountElements());
            storagePanel.getCarsStorageBar().setValue(factory.getCarsStorage().currentCountElements());

            storagePanel.getAccessoriesStorageCount().setText(factory.getAccessoriesStorage().currentCountElements() + "/" + factory.getAccessoriesStorage().getCapacity());
            storagePanel.getEnginesStorageCount().setText(factory.getEnginesStorage().currentCountElements() + "/" + factory.getEnginesStorage().getCapacity());
            storagePanel.getBodiesStorageCount().setText(factory.getBodiesStorage().currentCountElements() + "/" + factory.getBodiesStorage().getCapacity());
            storagePanel.getCarsStorageCount().setText(factory.getCarsStorage().currentCountElements() + "/" + factory.getCarsStorage().getCapacity());

            int count = 0;
            for (var status : factory.getCarBuilder().getStatuses()) {
                switch (status) {
                    case WAITING_FOR_DETAILS:
                        workersPanel.getStatuses().get(count).setText("Waiting for details");
                        break;
                    case WAITING_FOR_PLACE:
                        workersPanel.getStatuses().get(count).setText("Waiting for a place for a created car");
                        break;
                    case SLEEPING:
                        workersPanel.getStatuses().get(count).setText("Doing nothing");
                        break;
                    case WORKING:
                        workersPanel.getStatuses().get(count).setText("Building a car");
                        break;
                }
                ++count;
            }
        }
    }
}
