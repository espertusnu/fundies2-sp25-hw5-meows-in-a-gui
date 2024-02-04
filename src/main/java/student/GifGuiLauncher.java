package student;

import javax.swing.*;
import java.net.URL;
import java.util.List;

public class GifGuiLauncher implements GuiLauncher<Gif> {
    private List<Gif> GIFS = List.of(
            new Gif(
                    "No way cat GIF",
                    "https://64.media.tumblr.com/tumblr_lt7bswjhFd1r4ghkoo1_250.gifv"),
            new Gif("Well Done Applause GIF By MOODMAN",
                    "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExMHQ1dngxNjdvdThvZTR3dG90dmpuam4zcTJ0OHVnN3V0eDIyNnY5NyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/kBZBlLVlfECvOQAVno/giphy.gif")
    );

    @Override
    public String getName() {
        return "Animated GIFs";
    }

    @Override
    public void launchGui() {
        Display<Gif> display = new Display<>(
                GIFS, List.of());
        // TODO: Move this code into GuiLauncher, which should be made into an abstract class.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                display.setVisible(true);
            }
        });
    }
}
