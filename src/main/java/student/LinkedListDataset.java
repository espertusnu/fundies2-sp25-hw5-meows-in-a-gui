package student;

import java.util.LinkedList;

/**
 * Keeps track of a dataset of Rows of audio files. Each audio file is named
 * in the format "C_NNNNN_BB_SS_OOOOO_RXX.wav" where NNNNN is a unique cat ID,
 * R is the recording session, and XX is the vocalization counter.
 */
public class LinkedListDataset extends Dataset {

    public LinkedListDataset() {
        this.data = new LinkedList<>();
    }
}
