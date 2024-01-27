package student;

import java.util.ArrayList;

/**
 * Keeps track of a dataset of Rows of audio files. Each audio file is named
 * in the format "C_NNNNN_BB_SS_OOOOO_RXX.wav" where NNNNN is a unique cat ID,
 * R is the recording session, and XX is the vocalization counter.
 */
public class ArrayListDataset extends Dataset {
    public ArrayListDataset() {
        this.data = new ArrayList<>();
    }
}
