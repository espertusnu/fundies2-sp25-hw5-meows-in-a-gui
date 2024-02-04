package student;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A launcher enabling sorting and selecting recorded cat vocalizations.
 */
public class MeowGuiLauncher implements GuiLauncher<Meow> {
    private static final String TEST_FILES_PATH = "src/main/resources/test_files";

    /**
     * Constructs the launcher.
     */
    public MeowGuiLauncher() {}

    @Override
    public String getName() {
        return "Meow Collection";
    }

    private List<Meow> loadData(String path) {
        File[] files = new File(path).listFiles();
        List<Meow> meows = new ArrayList<>(); // TODO: Add capacity argument
        if (files != null) {
            for (File file : files) {
                meows.add(new Meow(file));
            }
        }
        return meows;
    }

    @Override
    public void launchGui() {
        Display<Meow> display = new Display<>(loadData(TEST_FILES_PATH), Meow.COMPARATORS);
        // TODO: Move this code into GuiLauncher, which should be made into an abstract class.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                display.setVisible(true);
            }
        });
    }
}
