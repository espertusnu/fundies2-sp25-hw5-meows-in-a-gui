package student;

import java.util.ArrayList;

/**
 * Keeps track of a dataset of Rows of audio files using an {@link ArrayList}.
 * The data format is described in {@link Dataset}.
 */
public class ArrayListDataset extends Dataset {
    /**
     * Constructs an empty data set.
     */
    public ArrayListDataset() {
        this.data = new ArrayList<>();
    }
}
