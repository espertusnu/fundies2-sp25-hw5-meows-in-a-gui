package student;

import com.google.common.annotations.VisibleForTesting;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a cat vocalization.
 */
public class Meow implements Playable {
    @VisibleForTesting
    static final NamedComparator<Meow> INCREASING_CAT_ID_NCOMPARATOR =
            new NamedComparator<Meow>("sort by increasing cat ID",
                    new Comparator<Meow>() {
                        @Override
                        public int compare(Meow o1, Meow o2) {
                            return o1.catID.compareTo(o2.catID);
                        }
                    }
            );
    @VisibleForTesting
    static final NamedComparator<Meow> INCREASING_RECORDING_ID_NCOMPARATOR =
            new NamedComparator<Meow>("sort by increasing recording ID",
                    new Comparator<Meow>() {
                        @Override
                        public int compare(Meow o1, Meow o2) {
                            return o1.recordingSessionVocalCounter - o2.recordingSessionVocalCounter;
                        }
                    }
            );
    @VisibleForTesting
    static final List<NamedComparator<Meow>> COMPARATORS = List.of(
            INCREASING_CAT_ID_NCOMPARATOR,
            INCREASING_RECORDING_ID_NCOMPARATOR
    );

    private final File audioFile;
    private final String catID;
    private final int recordingSessionVocalCounter;

    /**
     * Constructs a meow from the specified file
     *
     * @param file the file
     */
    public Meow(File file) {
        audioFile = file;
        String[] splittedFileName = file.getName().split("_");
        catID = splittedFileName[1];
        String sessionAsString = splittedFileName[splittedFileName.length - 1].replace(".wav", "");
        recordingSessionVocalCounter = Integer.parseInt(sessionAsString);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Meow meow) {
            return audioFile.equals(meow.audioFile);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return audioFile.hashCode();
    }


    // TODO: Add toString()

    /**
     * Plays the audio clip of the cat sound. If the sound cannot be played, it
     * prints a message saying it could not be played.
     */
    @Override
    public void play() {
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(audioFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            System.out.printf("Could not play %s\n", audioFile);
        }
    }
}
