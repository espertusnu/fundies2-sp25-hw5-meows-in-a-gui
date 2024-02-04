package student;

/**
 * This interface encapsulates the name and ability to launch a graphical
 * user interface enabling the user to sort and select playable items.
 *
 * @param <T> the type of the items
 */
public interface GuiLauncher<T extends Playable> {
    /**
     * Gets the name of this launcher.
     *
     * @return the name of this launcher
     */
    String getName();

    /**
     * Launches a GUI for sorting and selecting playable items.
     */
    void launchGui();
}
