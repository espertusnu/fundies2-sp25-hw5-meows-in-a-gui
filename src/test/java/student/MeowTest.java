package student;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

class MeowTest {
    // TODO: Create an instance variable meows with 4 different instances.
    // To do this, you will need to create a new Meow constructor
    // that takes a cat ID and recording session vocalization counter
    // and is visible for testing.
    //
    // Because Meow instances are immutable, you do not need a @BeforeEach method.

    Comparator<Meow> increasingCatIdComparator = Meow.INCREASING_CAT_ID_NCOMPARATOR.getComparator();
    Comparator<Meow> increasingRecordingIdComparator = Meow.INCREASING_RECORDING_ID_NCOMPARATOR.getComparator();

    @Test
    public void testIncreasingCatIdComparator() {
        // TODO: Write tests.
    }

    @Test
    public void testIncreasingRecordingIdComparator() {
        // TODO: Write tests.
    }
}