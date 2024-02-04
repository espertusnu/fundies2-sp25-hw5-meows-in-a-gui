package student;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MeowGuiLauncher implements GuiLauncher<Meow> {
    private static final String TEST_FILES_PATH = "src/main/resources/test_files";
    @Override
    public String getName() {
        return "Meow";
    }

    private List<Meow> loadData(String path) {
        List<Meow> meows = new ArrayList<>(); // TODO: Add capacity argument
        File[] files = new File(path).listFiles();
        if (files != null) {
            for (File file : files) {
                meows.add(new Meow(file));
            }
        }
        return meows;
    }

    @Override
    public void launchGui() {
        Display<Meow> display = new Display<>(
                loadData(TEST_FILES_PATH), List.of(Meow.INCREASING_CAT_ID_COMPARATOR, Meow.INCREASING_RECORDING_ID_COMPARATOR));
        // TODO: Move this code into GuiLauncher, which should be made into an abstract class.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                display.setVisible(true);
            }
        });
    }
}
