package student;

public interface GuiLauncher<T extends Playable> {
    String getName();

    void launchGui();
}
