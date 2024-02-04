package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
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

    public static void main(String[] args) {
        Dataset dataset = new ArrayListDataset();
        String testFilePath = "src/main/resources/test_files";
        File[] files = new File(testFilePath).listFiles();
        dataset.addEachToBack(files);
        NamedComparator<Meow> namedComparator1 =
                new NamedComparator("sort by increasing cat ID",
                        new Comparator<Meow>() {
                            @Override
                            public int compare(Meow o1, Meow o2) {
                                return o1.catID.compareTo(o2.catID);
                            }
                        }
                );
        NamedComparator<Meow> namedComparator2 =
                new NamedComparator("sort by increasing recording ID",
                        new Comparator<Meow>() {
                            @Override
                            public int compare(Meow o1, Meow o2) {
                                return o1.recordingSessionVocalCounter - o2.recordingSessionVocalCounter;
                            }
                        }
                );

        Display display = new Display(
                dataset.data, List.of(namedComparator1, namedComparator2));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                display.setVisible(true);
            }
        });
    }
}
