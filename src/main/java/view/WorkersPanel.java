package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WorkersPanel extends JPanel {
    private final ArrayList<JLabel> workers;
    private final ArrayList<JLabel> statuses;

    public WorkersPanel(Integer size) {

        workers = new ArrayList<>();
        statuses = new ArrayList<>();

        this.setLayout(new GridLayout(size, 2));

        int count = 0;
        for (int i = 0; i < size; i++) {
            workers.add(new JLabel("Worker #" + ++count));
            statuses.add(new JLabel());

            add(workers.get(i));
            add(statuses.get(i));
        }
    }

    public ArrayList<JLabel> getStatuses() {
        return statuses;
    }
}
