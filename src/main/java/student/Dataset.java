package student;

import java.io.File;
import java.util.*;

/**
 * Keeps track of a dataset of Meows of audio files. Each audio file is named
 * in the format "C_NNNNN_BB_SS_OOOOO_RXX.wav" where NNNNN is a unique cat ID,
 * R is the recording session, and XX is the vocalization counter.
 */
public abstract class Dataset {
    protected List<Meow> data;
    private Random rand;

    private void addEach(File[] files, boolean back) {
        for (File file : files) {
            int index = 0;
            if (back) {
                index = data.size();
            }
            data.add(index, (new Meow(file)));
        }
    }

    /**
     * Adds each file to the front of the dataset.
     * E.g., if the array contains [file1, file2], then
     * first file1 will be added to the front of the data, and
     * then file2 will be added in front of that.
     * This assumes the filenames follows the convention specified.
     *
     * @param files the array of files to be added to the dataset
     */
    public void addEachToFront(File[] files) {
        addEach(files, false);
    }

    /**
     * Adds each file to the end of the dataset.
     * E.g., if the array contains [file1, file2], then
     * first file1 will be added to the back of the data, and
     * then file2 will be added after that.
     * This assumes the filenames follows the convention specified.
     *
     * @param files the array of files to be added to the dataset
     */
    public void addEachToBack(File[] files) {
        addEach(files, true);
    }

    /**
     * Gets a random Meow from the data set.
     *
     * @return a randomly selected Meow from the dataset
     * @throws IllegalStateException if the dataset is empty
     */
    public Meow getRandomMeow() throws IllegalStateException {
        if (data.isEmpty()) {
            throw new IllegalStateException("The dataset must not be empty.");
        }
        if (rand == null) {
            rand = new Random();
        }
        int index = rand.nextInt(data.size());
        return data.get(index);
    }
}
