package student;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * An animated gif.
 */
public class Gif implements Playable {
    private static final NamedComparator<Gif> INCREASING_NAME_NCOMPARATOR = new NamedComparator<Gif>(
            "sort alphabetically", null);
    private static final NamedComparator<Gif> DECREASING_NAME_NCOMPARATOR = new NamedComparator<Gif>(
            "sort reverse alphabetically", null);

    private static final NamedComparator<Gif> INCREASING_NAME_LENGTH_NCOMPARATOR = new NamedComparator<Gif>(
            "sort by length", null);

    static final List<NamedComparator<Gif>> COMPARATORS = List.of(
            INCREASING_NAME_NCOMPARATOR,
            DECREASING_NAME_NCOMPARATOR,
            INCREASING_NAME_LENGTH_NCOMPARATOR
    );

    private final String name;
    private final String urlString;

    /**
     * Constructs an animated gif.
     *
     * @param name      the name to be displayed
     * @param urlString the URL of the gif
     */
    public Gif(String name, String urlString) {
        this.name = name;
        this.urlString = urlString;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void play() {
        try {
            JFrame frame = new JFrame(name);
            ImageIcon icon = new ImageIcon(new URL(urlString));
            frame.add(new JLabel(icon));
            frame.setSize(icon.getIconWidth(), icon.getIconHeight());
            frame.setVisible(true);
        } catch (MalformedURLException e) {
            System.out.printf("Unable to display %s\n", name);
        }
    }
}
