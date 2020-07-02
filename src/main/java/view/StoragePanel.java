package view;

import main.FactoryConfig;

import javax.swing.*;

public class StoragePanel extends JPanel {
    private final JProgressBar accessoriesStorage;
    private final JProgressBar enginesStorage;
    private final JProgressBar bodiesStorage;
    private final JProgressBar carsStorage;

    private final JLabel accessoriesLabel;
    private final JLabel enginesLabel;
    private final JLabel bodiesLabel;
    private final JLabel carsLabel;

    private final JLabel accessoriesCount;
    private final JLabel enginesCount;
    private final JLabel bodiesCount;
    private final JLabel carsCount;

    private final GroupLayout layout;

    public StoragePanel(FactoryConfig factoryConfig) {

        accessoriesStorage = new JProgressBar(0, factoryConfig.getAccessoriesStorageSize());
        enginesStorage = new JProgressBar(0, factoryConfig.getEnginesStorageSize());
        bodiesStorage = new JProgressBar(0, factoryConfig.getBodiesStorageSize());
        carsStorage = new JProgressBar(0, factoryConfig.getCarsStorageSize());

        accessoriesLabel = new JLabel("Accessories storage");
        enginesLabel = new JLabel("Engines storage");
        bodiesLabel = new JLabel("Bodies storage");
        carsLabel = new JLabel("Cars storage");

        accessoriesCount = new JLabel("0/" + factoryConfig.getAccessoriesStorageSize());
        bodiesCount = new JLabel("0/" + factoryConfig.getBodiesStorageSize());
        enginesCount = new JLabel("0/" + factoryConfig.getEnginesStorageSize());
        carsCount = new JLabel("0/" + factoryConfig.getCarsStorageSize());

        carsStorage.setBorderPainted(true);

        layout = new GroupLayout(this);

        configureLayout();

        this.setLayout(layout);
    }

    private void configureLayout() {
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(accessoriesLabel)
                                .addComponent(bodiesLabel)
                                .addComponent(enginesLabel)
                                .addComponent(carsLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(accessoriesCount)
                                .addComponent(bodiesCount)
                                .addComponent(enginesCount)
                                .addComponent(carsCount)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(accessoriesStorage)
                                .addComponent(bodiesStorage)
                                .addComponent(enginesStorage)
                                .addComponent(carsStorage)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(accessoriesLabel)
                                .addComponent(accessoriesCount)
                                .addComponent(accessoriesStorage)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(bodiesLabel)
                                .addComponent(bodiesCount)
                                .addComponent(bodiesStorage)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(enginesLabel)
                                .addComponent(enginesCount)
                                .addComponent(enginesStorage)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(carsLabel)
                                .addComponent(carsCount)
                                .addComponent(carsStorage)
                        )
        );
    }

    public JProgressBar getAccessoriesStorageBar() {
        return accessoriesStorage;
    }

    public JProgressBar getEnginesStorageBar() {
        return enginesStorage;
    }

    public JProgressBar getBodiesStorageBar() {
        return bodiesStorage;
    }

    public JProgressBar getCarsStorageBar() {
        return carsStorage;
    }

    public JLabel getAccessoriesStorageCount() {
        return accessoriesCount;
    }

    public JLabel getEnginesStorageCount() {
        return enginesCount;
    }

    public JLabel getBodiesStorageCount() {
        return bodiesCount;
    }

    public JLabel getCarsStorageCount() {
        return carsCount;
    }

}
