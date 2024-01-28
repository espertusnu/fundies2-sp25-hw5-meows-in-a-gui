package student;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;

/**
 * Keeps track of a dataset of Rows of audio files. Each audio file is named
 * in the format "C_NNNNN_BB_SS_OOOOO_RXX.wav" where NNNNN is a unique cat ID,
 * R is the recording session, and XX is the vocalization counter.
 */
public abstract class Dataset {
    protected List<Row> data;
    private Random rand;

    private void addEach(String path, boolean back) {
        File[] files = new File(path).listFiles();
        Arrays.sort(files);
        for (File file : files) {
            int index = 0;
            if (back) {
                index = data.size();
            }
            data.add(index, (new Row(file)));
        }
    }

    /**
     * Adds each file in the specified directory to the front of the dataset, in alphabetical order by filename.
     * E.g., if the directory contains file1 and file2, and file1 is before file2 alphabetically, then
     * first file1 will be added to the front of the data, and then file2 will be added in front of that.
     * This assumes the filename follows the convention specified.
     *
     * @param path the location of the directory containing the files
     */
    public void addEachToFront(String path) {
        addEach(path, false);
    }

    /**
     * Adds each file in the directory specified to the end of the dataset, in alphabetical order by filename.
     * E.g., if the directory contains file1 and file2, and file1 is before file2 alphabetically, then
     * first file1 will be added to the back of the data, and then file2 will be added after that.
     * This assumes the filename follows the convention specified.
     *
     * @param path the location of the directory containing the files
     */
    public void addEachToBack(String path) {
        addEach(path, true);
    }

    /**
     * Gets a random Row from the data set.
     *
     * @return a randomly selected Row from the dataset
     * @throws IllegalStateException if the dataset is empty
     */
    public Row getRandomRow() throws IllegalStateException {
        if (data.isEmpty()) {
            throw new IllegalStateException("The dataset must not be empty.");
        }
        if (rand == null) {
            rand = new Random();
        }
        int index = rand.nextInt(data.size());
        return data.get(index);
    }

    /**
     * Sort the Rows in the dataset. Rows are primarily ordered by cat ID. If two Rows
     * have the same cat ID, they are ordered by recording session and vocalization counter.
     */
    public void sortDataset() {
        Collections.sort(data);
    }


    /**
     * Represents a Row in the dataset of cat sounds. Keeps track of the audio file,
     * the cat ID, and the recording session plus vocal counter.
     */
    public static class Row implements Comparable<Row> {

        protected File audioFile;
        protected String catID;
        protected int recordingSessionVocalCounter;

        protected Row(File file) {
            audioFile = file;
            String[] splittedFileName = file.getName().split("_");
            catID = splittedFileName[1];
            String sessionAsString = splittedFileName[splittedFileName.length - 1].replace(".wav", "");
            recordingSessionVocalCounter = Integer.parseInt(sessionAsString);
        }

        @Override
        public int compareTo(Row other) {
            if (catID.equals(other.catID)) {
                return recordingSessionVocalCounter - other.recordingSessionVocalCounter;
            } else {
                return catID.compareTo(other.catID);
            }
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Row row) {
                return audioFile.equals(row.audioFile);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return audioFile.hashCode();
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
}
