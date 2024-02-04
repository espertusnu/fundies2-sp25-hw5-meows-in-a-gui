package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

class MeowTest {
    List<Meow> meows;
    Comparator<Meow> increasingCatIdComparator = Meow.INCREASING_CAT_ID_NCOMPARATOR.getComparator();
    Comparator<Meow> increasingRecordingIdComparator = Meow.INCREASING_RECORDING_ID_NCOMPARATOR.getComparator();

    @BeforeEach
    public void setup() {
        // TODO: Initialize meows with 4 different instances.
        // To do this, you will need to create a new Meow constructor
        // that takes a cat ID and recording session vocalization counter
        // and is visible for testing. Make sure that the concrete type
        // of meows supports all optional operations.
    }

    @Test
    public void testIncreasingCatIdComparator() {
        // TODO: Write tests.
    }

    @Test
    public void testIncreasingRecordingIdComparator() {
        // TODO: Write tests.
    }
}