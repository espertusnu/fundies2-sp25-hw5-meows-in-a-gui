package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class Display<T extends Playable> extends JFrame {
    private List<T> playables;
    private List<NamedComparator<T>> comparators;
    private DefaultListModel<Playable> listModel;
    private JList<Playable> jList;

    public Display(List<T> playables, List<NamedComparator<T>> comparators) {
        this.playables = playables;
        this.comparators = comparators;
        this.listModel = new DefaultListModel<>();
        this.jList = new JList<>(listModel);

        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Homework 5 GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        updateJList();
        JPanel buttonPanel = new JPanel();

        for (NamedComparator<T> namedComparator : comparators) {
            JButton button = new JButton(namedComparator.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Collections.sort(playables, namedComparator.getComparator());
                    updateJList();
                }
            });
            buttonPanel.add(button);
        }

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Playable playable = jList.getSelectedValue();
                if (playable != null) {
                    playable.play();
                } else {
                    JOptionPane.showMessageDialog(Display.this, "Please select an item to play.");
                }
            }
        });

        buttonPanel.add(playButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(jList), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateJList() {
        listModel.clear();
        for (Playable playable : playables) {
            listModel.addElement(playable);
        }
    }
}
