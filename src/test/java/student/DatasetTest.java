package student;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatasetTest {
    Dataset dataset;
    String testFilePath = "src/main/resources/test_files";
    String filenameOne = "/B_ANI01_MC_FN_SIM01_101.wav";
    String filenameTwo = "/B_ANI01_MC_FN_SIM01_102.wav";
    File[] files;

    @BeforeEach
    void setup() {
        dataset = new ArrayListDataset();
        files = new File(testFilePath).listFiles();
        Arrays.sort(files);
    }

    @Test
    void addEachToFrontOrder() {
        dataset.addEachToFront(files);
        List<Meow> expected = new ArrayList<>();
        expected.add(new Meow(new File(testFilePath + filenameTwo)));
        expected.add(new Meow(new File(testFilePath + filenameOne)));
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToFrontOnNonEmptyDataset() {
        dataset.data = new ArrayList<>();
        dataset.data.add(new Meow(new File(testFilePath + filenameOne)));
        dataset.data.add(new Meow(new File(testFilePath + filenameTwo)));
        dataset.addEachToFront(files);
        List<Meow> expected = List.of(
                new Meow(new File(testFilePath + filenameTwo)),
                new Meow(new File(testFilePath + filenameOne)),
                new Meow(new File(testFilePath + filenameOne)),
                new Meow(new File(testFilePath + filenameTwo))
        );
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToFrontEmptyDirectory() {
        dataset.addEachToFront(new File[0]);
        assertEquals(new ArrayList<Meow>(), dataset.data);
    }

    @Test
    void addEachToFrontDoesNotChangeFileArray() {
        File[] filesCopy = Arrays.copyOf(files, files.length);
        dataset.addEachToFront(files);
        assertArrayEquals(filesCopy, files);
    }

    @Test
    void addEachToBackOrder() {
        dataset.addEachToBack(files);
        List<Meow> expected = List.of(
                new Meow(new File(testFilePath + filenameOne)),
                new Meow(new File(testFilePath + filenameTwo))
        );
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToBackOnNonEmptyDataset() {
        dataset.data = new ArrayList<>();
        dataset.data.add(new Meow(new File(testFilePath + filenameOne)));
        dataset.data.add(new Meow(new File(testFilePath + filenameTwo)));
        dataset.addEachToBack(files);
        List<Meow> expected = List.of(
                new Meow(new File(testFilePath + filenameOne)),
                new Meow(new File(testFilePath + filenameTwo)),
                new Meow(new File(testFilePath + filenameOne)),
                new Meow(new File(testFilePath + filenameTwo))
        );
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToBackEmptyDirectory() {
        dataset.addEachToBack(new File[0]);
        assertEquals(new ArrayList<Meow>(), dataset.data);
    }

    @Test
    void getRandomMeowWorks() {
        dataset.addEachToFront(files);
        try {
            for (int i = 0; i < 50; i++) {
                Meow meow = dataset.getRandomMeow();
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getRandomMeowEmptyDatasetException() {
        assertThrows(
                IllegalStateException.class,
                () -> dataset.getRandomMeow()
        );
    }

    @Test
    void sortDatasetWorks() {
        dataset.addEachToBack(files);
        dataset.addEachToFront(files);
        dataset.sortDataset();
        List<Meow> expected = List.of(
                new Meow(new File(testFilePath + filenameOne)),
                new Meow(new File(testFilePath + filenameOne)),
                new Meow(new File(testFilePath + filenameTwo)),
                new Meow(new File(testFilePath + filenameTwo))
        );
        assertEquals(expected, dataset.data);
    }

    @Test
    void sortDatasetEmptyWorks() {
        try {
            dataset.sortDataset();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void compareToDifferentCatID() {
        Meow meow1 = new Meow(new File(testFilePath + filenameOne));
        Meow meow2 = new Meow(new File(testFilePath + filenameOne));
        meow1.catID = "Z" + meow1.catID;
        assertTrue(meow1.compareTo(meow2) > 0);
        assertTrue(meow2.compareTo(meow1) < 0);
    }

    @Test
    void compareToSameCatIDDifferentRecordVocal() {
        Meow meow1 = new Meow(new File(testFilePath + filenameOne));
        Meow meow2 = new Meow(new File(testFilePath + filenameTwo));
        assertTrue(meow1.compareTo(meow2) < 0);
        assertTrue(meow2.compareTo(meow1) > 0);
    }

    @Test
    void compareToSameCatIDRecordVocalDifferentFile() {
        Meow meow1 = new Meow(new File(testFilePath + filenameOne));
        Meow meow2 = new Meow(new File(testFilePath + filenameOne));
        meow2.recordingSessionVocalCounter = 101;
        assertEquals(0, meow1.compareTo(meow2));
        assertEquals(0, meow2.compareTo(meow1));
    }
}