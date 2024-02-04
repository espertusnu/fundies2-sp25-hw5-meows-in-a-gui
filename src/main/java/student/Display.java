package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A graphical user interface for sorting and selecting playable items.
 *
 * @param <T> the type of the items
 */
public class Display<T extends Playable> extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private final List<T> playables;
    private final List<NamedComparator<T>> comparators;
    private final DefaultListModel<Playable> listModel;
    private final JList<Playable> jList;

    /**
     * Constructs and launches a GUI that allows the provided data to be
     * reordered and played.
     *
     * @param playables the data items
     * @param comparators the comparators used to sort them
     */
    public Display(List<T> playables, List<NamedComparator<T>> comparators) {
        this.playables = new ArrayList<>(playables); // ensure mutability
        this.comparators = comparators;
        this.listModel = new DefaultListModel<>();
        this.jList = new JList<>(listModel);

        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Fundies 2 Homework 5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        updateJList();
        JPanel buttonPanel = new JPanel();

        for (NamedComparator<T> namedComparator : comparators) {
            JButton button = new JButton(namedComparator.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playables.sort(namedComparator.getComparator());
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
