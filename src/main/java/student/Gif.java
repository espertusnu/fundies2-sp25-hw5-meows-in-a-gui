package student;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

// TODO: Students will have to write this class.
public class Gif implements Playable {
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
