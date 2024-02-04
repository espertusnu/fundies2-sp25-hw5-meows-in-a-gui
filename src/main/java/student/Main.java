package student;

import java.util.List;
import java.util.Scanner;

/**
 * A program that launches a graphical user interface selected by the user.
 */
public class Main {
    private static List<GuiLauncher<?>> launchers = List.of(new MeowGuiLauncher(), new GifGuiLauncher());

    // prevent this class from being instantiated
    private Main() {}

    private static GuiLauncher<?> getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Options:");
            for (int i = 0; i < launchers.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, launchers.get(i).getName());
            }
            System.out.println("Enter your choice: ");
            // TODO: Add code to catch exceptions thrown by nextInt().
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= launchers.size()) {
                return launchers.get(choice - 1);
            } else {
                System.out.println("Your choice was out of range.");
            }
        }
    }

    /**
     * Launches the GUI of the user's choice.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        GuiLauncher<?> launcher = getUserChoice();
        launcher.launchGui();
    }
}
