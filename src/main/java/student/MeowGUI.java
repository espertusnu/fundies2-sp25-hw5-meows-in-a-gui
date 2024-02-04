package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeowGUI extends JFrame {
    private List<Meow> meowList;
    private DefaultListModel<Meow> listModel;
    private JList<Meow> meowJList;

    public MeowGUI(List<Meow> meowList) {
        this.meowList = meowList;
        this.listModel = new DefaultListModel<>();
        this.meowJList = new JList<>(listModel);

        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Meow GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        updateJList();

        JButton sortCatIdButton = new JButton("Sort by catId");
        sortCatIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortMeowList(new Comparator<Meow>() {
                    @Override
                    public int compare(Meow o1, Meow o2) {
                        return o1.catID.compareTo(o2.catID);
                    }
                });
            }
        });

        JButton sortCounterButton = new JButton("Sort by counter");
        sortCounterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortMeowList(new Comparator<Meow>() {
                    @Override
                    public int compare(Meow o1, Meow o2) {
                        return Integer.compare(o1.recordingSessionVocalCounter, o2.recordingSessionVocalCounter);
                    }
                });
            }
        });

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meow selectedMeow = meowJList.getSelectedValue();
                if (selectedMeow != null) {
                    selectedMeow.play();
                } else {
                    JOptionPane.showMessageDialog(MeowGUI.this, "Please select a Meow to play.");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortCatIdButton);
        buttonPanel.add(sortCounterButton);
        buttonPanel.add(playButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(meowJList), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateJList() {
        listModel.clear();
        for (Meow meow : meowList) {
            listModel.addElement(meow);
        }
    }

    private void sortMeowList(Comparator<Meow> comparator) {
        Collections.sort(meowList, comparator);
        updateJList();
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
