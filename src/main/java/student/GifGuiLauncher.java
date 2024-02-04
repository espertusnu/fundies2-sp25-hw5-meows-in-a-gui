package student;

import javax.swing.*;
import java.util.List;

public class GifGuiLauncher implements GuiLauncher<Gif> {
    private List<Gif> GIFS = List.of(
            new Gif(
                    "No way cat GIF",
                    "https://64.media.tumblr.com/tumblr_lt7bswjhFd1r4ghkoo1_250.gifv"),
            new Gif("Well Done Applause GIF By MOODMAN",
                    "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExMHQ1dngxNjdvdThvZTR3dG90dmpuam4zcTJ0OHVnN3V0eDIyNnY5NyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/kBZBlLVlfECvOQAVno/giphy.gif"),
            new Gif("NU snowglobe",
                "https://media4.giphy.com/media/SSc7JZMKlbEFfP6yEo/giphy.gif?cid=ecf05e47i93k0uzibpol8eo7kwixuz83z7urwect7h7jr5zo&ep=v1_gifs_related&rid=giphy.gif&ct=g"),
            new Gif("Think Artificial Intelligence GIF By Massive Science",
                    "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExemxnd29wYXM1bjR1dGozeWZnM3IxMGhuMmhkMTUyZWNjeXprMWNwcSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/WxJLwDBAXDsW1fqZ3v/giphy.gif"),
            new Gif("Code GIF", "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExNnM3cDR0Yjc3dGtpOG9rbmQyd2hxeTJ2ZXFldmgza3c0eGhyeDRqbCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/A06UFEx8jxEwU/giphy.gif")
    );

    @Override
    public String getName() {
        return "Animated GIFs";
    }

    @Override
    public void launchGui() {
        Display<Gif> display = new Display<>(GIFS, Gif.COMPARATORS);

        // TODO: Move this code into GuiLauncher, which should be made into an abstract class.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                display.setVisible(true);
            }
        });
    }
}
