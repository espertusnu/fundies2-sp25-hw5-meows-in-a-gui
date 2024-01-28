package student;

import java.util.LinkedList;

/**
 * Keeps track of a dataset of Rows of audio files using a {@link LinkedList}.
 * The data format is described in {@link Dataset}.
 */
public class LinkedListDataset extends Dataset {

    /**
     * Constructs an empty data set.
     */
    public LinkedListDataset() {
        this.data = new LinkedList<>();
    }
}
