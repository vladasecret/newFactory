package view;

import factory.Factory;

import javax.swing.*;
import java.util.Hashtable;

public class TimeSettingsPanel extends JPanel {
    private final JSlider accessoriesSupplierTime;
    private final JSlider bodiesSupplierTime;
    private final JSlider enginesSupplierTime;
    private final JSlider workersTime;
    private final JSlider dealersTime;

    private final JLabel accessoriesLabel;
    private final JLabel bodiesLabel;
    private final JLabel enginesLabel;
    private final JLabel dealersLabel;
    private final JLabel workersLabel;

    private final GroupLayout layout;

    int minTime = 1000;
    int maxTime = 30000;

    public TimeSettingsPanel(Factory f) {

        accessoriesSupplierTime = new JSlider(JSlider.HORIZONTAL, minTime, maxTime, f.getAccessoriesSuppliers().get(0).getPeriod());
        accessoriesSupplierTime.setPaintLabels(true);
        accessoriesSupplierTime.setMinorTickSpacing(1000);
        accessoriesSupplierTime.setPaintTicks(true);
        accessoriesSupplierTime.setSnapToTicks(true);

        bodiesSupplierTime = new JSlider(JSlider.HORIZONTAL, minTime, maxTime, f.getBodiesSupplier().getPeriod());
        bodiesSupplierTime.setPaintLabels(true);
        bodiesSupplierTime.setMinorTickSpacing(1000);
        bodiesSupplierTime.setPaintTicks(true);
        bodiesSupplierTime.setSnapToTicks(true);

        enginesSupplierTime = new JSlider(JSlider.HORIZONTAL, minTime, maxTime, f.getEnginesSupplier().getPeriod());
        enginesSupplierTime.setPaintLabels(true);
        enginesSupplierTime.setMinorTickSpacing(1000);
        enginesSupplierTime.setPaintTicks(true);
        enginesSupplierTime.setSnapToTicks(true);

        dealersTime = new JSlider(JSlider.HORIZONTAL, minTime, maxTime, f.getDealers().get(0).getPeriod());
        dealersTime.setPaintLabels(true);
        dealersTime.setMinorTickSpacing(1000);
        dealersTime.setPaintTicks(true);
        dealersTime.setSnapToTicks(true);

        workersTime = new JSlider(JSlider.HORIZONTAL, minTime, maxTime, f.getCarBuilder().getWorkTime());
        workersTime.setPaintLabels(true);
        workersTime.setMinorTickSpacing(1000);
        workersTime.setPaintTicks(true);
        workersTime.setSnapToTicks(true);

        Hashtable<Integer, JLabel> hs = new Hashtable<>();
        hs.put(accessoriesSupplierTime.getMinimum(), new JLabel(accessoriesSupplierTime.getMinimum() / 1000 + "sec"));
        hs.put(accessoriesSupplierTime.getMaximum(), new JLabel(accessoriesSupplierTime.getMaximum() / 1000 + "sec"));
        accessoriesSupplierTime.setLabelTable(hs);
        bodiesSupplierTime.setLabelTable(hs);
        enginesSupplierTime.setLabelTable(hs);
        dealersTime.setLabelTable(hs);
        workersTime.setLabelTable(hs);

        accessoriesLabel = new JLabel("Supplying time per accessory: " + accessoriesSupplierTime.getValue() / 1000 + " sec");
        bodiesLabel = new JLabel("Supplying time per body: " + bodiesSupplierTime.getValue() / 1000 + " sec");
        enginesLabel = new JLabel("Supplying time per engine: " + enginesSupplierTime.getValue() / 1000 + " sec");
        dealersLabel = new JLabel("Dealer time per car: " + dealersTime.getValue() / 1000 + " sec");
        workersLabel = new JLabel("Worker time per car: " + workersTime.getValue() / 1000 + " sec");

        layout = new GroupLayout(this);
        configureLayout();

        accessoriesSupplierTime.addChangeListener(
                e -> {
                    for (var supplier : f.getAccessoriesSuppliers()) {
                        supplier.setPeriod(accessoriesSupplierTime.getValue());
                    }
                    accessoriesLabel.setText("Supplying time per accessory: " + accessoriesSupplierTime.getValue() / 1000 + " sec");
                }
        );

        bodiesSupplierTime.addChangeListener(
                e -> {
                    f.getBodiesSupplier().setPeriod((bodiesSupplierTime.getValue()));
                    bodiesLabel.setText("Supplying time per body: " + bodiesSupplierTime.getValue() / 1000 + " sec");
                }
        );

        enginesSupplierTime.addChangeListener(
                e -> {
                    f.getEnginesSupplier().setPeriod(enginesSupplierTime.getValue());
                    enginesLabel.setText("Supplying time per engine: " + enginesSupplierTime.getValue() / 1000 + " sec");
                }
        );

        dealersTime.addChangeListener(
                e -> {
                    for (var dealer : f.getDealers()) {
                        dealer.setPeriod(dealersTime.getValue());
                    }
                    dealersLabel.setText("Dealer time per car: " + dealersTime.getValue() / 1000 + " sec");
                }
        );

        workersTime.addChangeListener(
                e -> {
                    f.getCarBuilder().setWorkTime(workersTime.getValue());
                    workersLabel.setText("Worker time per car: " + workersTime.getValue() / 1000 + " sec");
                }
        );

        setLayout(layout);
    }

    private void configureLayout() {
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(accessoriesLabel)
                                .addComponent(accessoriesSupplierTime))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(bodiesLabel)
                                .addComponent(bodiesSupplierTime))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(enginesLabel)
                                .addComponent(enginesSupplierTime))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(workersLabel)
                                .addComponent(workersTime))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(dealersLabel)
                                .addComponent(dealersTime))

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(accessoriesLabel)
                        .addComponent(accessoriesSupplierTime)
                        .addComponent(bodiesLabel)
                        .addComponent(bodiesSupplierTime)
                        .addComponent(enginesLabel)
                        .addComponent(enginesSupplierTime)
                        .addComponent(workersLabel)
                        .addComponent(workersTime)
                        .addComponent(dealersLabel)
                        .addComponent(dealersTime)
        );
    }
}
