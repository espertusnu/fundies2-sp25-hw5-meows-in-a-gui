package student;

import javax.swing.*;
import java.net.URL;

// TODO: Students will have to write this class.
public class AnimatedGif implements Playable {
    private final String name;
    private final URL url;

    public AnimatedGif(String name, URL url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void play() {
        ImageIcon icon = new ImageIcon(url);
        JFrame frame = new JFrame(name);
        frame.add(new JLabel(icon));
        frame.setSize(icon.getIconWidth(), icon.getIconHeight());
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        AnimatedGif gif = new AnimatedGif(
                "No way cat",
                new URL("https://64.media.tumblr.com/tumblr_lt7bswjhFd1r4ghkoo1_250.gifv"));
        gif.play();
    }
}
