package student;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Represents a Meow in the dataset of cat sounds. Keeps track of the audio file,
 * the cat ID, and the recording session plus vocal counter.
 */
public class Meow implements Comparable<Meow> {

    protected File audioFile;
    public String catID;
    public int recordingSessionVocalCounter;

    protected Meow(File file) {
        audioFile = file;
        String[] splittedFileName = file.getName().split("_");
        catID = splittedFileName[1];
        String sessionAsString = splittedFileName[splittedFileName.length - 1].replace(".wav", "");
        recordingSessionVocalCounter = Integer.parseInt(sessionAsString);
    }

    @Override
    public int compareTo(Meow other) {
        if (catID.equals(other.catID)) {
            return recordingSessionVocalCounter - other.recordingSessionVocalCounter;
        } else {
            return catID.compareTo(other.catID);
        }
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
    public String toString() {
        return String.format("Cat %s, Counter %d", catID, recordingSessionVocalCounter);
    }

    /**
     * Plays the audio clip of the cat sound. If the sound cannot be played, it
     * prints a message saying it could not be played.
     */
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
