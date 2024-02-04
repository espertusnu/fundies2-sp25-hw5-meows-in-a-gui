package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class MeowGUI<T extends Playable> extends JFrame {
    private SortableList<T> sortableList;
    private List<T> playableList;
    private DefaultListModel<Playable> listModel;
    private JList<Playable> jList;

    public MeowGUI(SortableList<T> sortableList) {
        this.sortableList = sortableList;
        this.playableList = sortableList.getData();
        this.listModel = new DefaultListModel<>();
        this.jList = new JList<>(listModel);

        initializeGUI();
    }

    private <T extends Playable> void initializeGUI() {
        setTitle("Homework 5 GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        updateJList();
        JPanel buttonPanel = new JPanel();
        List<NamedComparator<T>> comparators = sortableList.getComparators();

        for (NamedComparator<T> namedComparator : comparators) {
            JButton button = new JButton(namedComparator.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Collections.sort(sortableList.getData(), namedComparator.getComparator());
                    updateJList();
                }
            });
            buttonPanel.add(button);
        }

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meow selectedMeow = jList.getSelectedValue();
                if (selectedMeow != null) {
                    selectedMeow.play();
                } else {
                    JOptionPane.showMessageDialog(MeowGUI.this, "Please select a Meow to play.");
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
        for (Playable playable : playableList) {
            listModel.addElement(playable);
        }
    }

    public static void main(String[] args) {
        Dataset dataset = new ArrayListDataset();
        String testFilePath = "src/main/resources/test_files";
        File[] files = new File(testFilePath).listFiles();
        dataset.addEachToBack(files);

        MeowGUI meowGUI = new MeowGUI(dataset.data);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                meowGUI.setVisible(true);
            }
        });
    }
}
