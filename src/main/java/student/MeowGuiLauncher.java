package student;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class MeowGuiLauncher implements GuiLauncher<Meow> {

    @Override
    public String getName() {
        return "Meow";
    }

    @Override
    public void launchGui() {
        Dataset dataset = new ArrayListDataset();
        String testFilePath = "src/main/resources/test_files";
        File[] files = new File(testFilePath).listFiles();
        dataset.addEachToBack(files);

        Display display = new Display(
                dataset.data, List.of(Meow.INCREASING_CAT_ID_COMPARATOR, Meow.INCREASING_RECORDING_ID_COMPARATOR));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                display.setVisible(true);
            }
        });
    }
}
