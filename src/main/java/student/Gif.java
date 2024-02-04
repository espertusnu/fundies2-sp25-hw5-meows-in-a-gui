package student;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

// TODO: Students will have to write this class.
public class Gif implements Playable {
    private static final NamedComparator<Gif> INCREASING_NAME_COMPARATOR = new NamedComparator<Gif>(
            "sort alphabetically",
            new Comparator<Gif>() {
                @Override
                public int compare(Gif gif1, Gif gif2) {
                    return gif1.name.compareTo(gif2.name);
                }
            });
    private static final NamedComparator<Gif> DECREASING_NAME_COMPARATOR = new NamedComparator<Gif>(
            "sort reverse alphabetically",
            new Comparator<Gif>() {
                @Override
                public int compare(Gif gif1, Gif gif2) {
                    return gif2.name.compareTo(gif1.name);
                }
            });
    private static final NamedComparator<Gif> INCREASING_NAME_LENGTH_COMPARATOR = new NamedComparator<Gif>(
            "sort by length",
            new Comparator<Gif>() {
                @Override
                public int compare(Gif gif1, Gif gif2) {
                    return gif1.name.length() - gif2.name.length();
                }
            });

    static final List<NamedComparator<Gif>> COMPARATORS = List.of(
            INCREASING_NAME_COMPARATOR,
            INCREASING_NAME_LENGTH_COMPARATOR
    );

    private final String name;
    private final String urlString;

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
        JFrame frame = new JFrame(name);
        try {
            ImageIcon icon = new ImageIcon(new URL(urlString));
            frame.add(new JLabel(icon));
            frame.setSize(icon.getIconWidth(), icon.getIconHeight());
        } catch (MalformedURLException e) {
            JLabel label = new JLabel(name);
            label.setPreferredSize(new Dimension(200, 100));
            frame.getContentPane().add(label);
            frame.setSize(label.getPreferredSize());
        }
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Gif gif = new Gif(
                "No way cat",
                "https://64.media.tumblr.com/tumblr_lt7bswjhFd1r4ghkoo1_250.gifv");
        gif.play();
    }
}
