package student;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatasetTest {
    Dataset dataset;
    String testFilePath = "data/test_files";
    String emptyDir = "data/empty";
    String filenameOne = "/B_ANI01_MC_FN_SIM01_101.wav";
    String filenameTwo = "/B_ANI01_MC_FN_SIM01_102.wav";

    @BeforeEach
    void setup() {
        dataset = new ArrayListDataset();
    }

    @Test
    void addEachToFrontOrder() {
        dataset.addEachToFront(testFilePath);
        List<Dataset.Row> expected = new ArrayList<>();
        expected.add(new Dataset.Row(new File(testFilePath + filenameTwo)));
        expected.add(new Dataset.Row(new File(testFilePath + filenameOne)));
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToFrontOnNonEmptyDataset() {
        dataset.data = new ArrayList<>();
        dataset.data.add(new Dataset.Row(new File(testFilePath + filenameOne)));
        dataset.data.add(new Dataset.Row(new File(testFilePath + filenameTwo)));
        dataset.addEachToFront(testFilePath);
        List<Dataset.Row> expected = List.of(
                new Dataset.Row(new File(testFilePath + filenameTwo)),
                new Dataset.Row(new File(testFilePath + filenameOne)),
                new Dataset.Row(new File(testFilePath + filenameOne)),
                new Dataset.Row(new File(testFilePath + filenameTwo))
        );
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToFrontEmptyDirectory() {
        dataset.addEachToFront(emptyDir);
        assertEquals(new ArrayList<Dataset.Row>(), dataset.data);
    }

    @Test
    void addEachToBackOrder() {
        dataset.addEachToBack(testFilePath);
        List<Dataset.Row> expected = List.of(
                new Dataset.Row(new File(testFilePath + filenameOne)),
                new Dataset.Row(new File(testFilePath + filenameTwo))
        );
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToBackOnNonEmptyDataset() {
        dataset.data = new ArrayList<>();
        dataset.data.add(new Dataset.Row(new File(testFilePath + filenameOne)));
        dataset.data.add(new Dataset.Row(new File(testFilePath + filenameTwo)));
        dataset.addEachToBack(testFilePath);
        List<Dataset.Row> expected = List.of(
                new Dataset.Row(new File(testFilePath + filenameOne)),
                new Dataset.Row(new File(testFilePath + filenameTwo)),
                new Dataset.Row(new File(testFilePath + filenameOne)),
                new Dataset.Row(new File(testFilePath + filenameTwo))
        );
        assertEquals(expected, dataset.data);
    }

    @Test
    void addEachToBackEmptyDirectory() {
        dataset.addEachToBack(emptyDir);
        assertEquals(new ArrayList<Dataset.Row>(), dataset.data);
    }

    @Test
    void getRandomRowWorks() {
        dataset.addEachToFront(testFilePath);
        try {
            for (int i = 0; i < 50; i++) {
                Dataset.Row row = dataset.getRandomRow();
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getRandomRowEmptyDatasetException() {
        assertThrows(
                IllegalStateException.class,
                () -> dataset.getRandomRow()
        );
    }

    @Test
    void sortDatasetWorks() {
        dataset.addEachToBack(testFilePath);
        dataset.addEachToFront(testFilePath);
        dataset.sortDataset();
        List<Dataset.Row> expected = List.of(
                new Dataset.Row(new File(testFilePath + filenameOne)),
                new Dataset.Row(new File(testFilePath + filenameOne)),
                new Dataset.Row(new File(testFilePath + filenameTwo)),
                new Dataset.Row(new File(testFilePath + filenameTwo))
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
    void rowCompareToDifferentCatID() {
        Dataset.Row row1 = new Dataset.Row(new File(testFilePath + filenameOne));
        Dataset.Row row2 = new Dataset.Row(new File(testFilePath + filenameOne));
        row1.catID = "Z" + row1.catID;
        assertTrue(row1.compareTo(row2) > 0);
        assertTrue(row2.compareTo(row1) < 0);
    }

    @Test
    void rowCompareToSameCatIDDifferentRecordVocal() {
        Dataset.Row row1 = new Dataset.Row(new File(testFilePath + filenameOne));
        Dataset.Row row2 = new Dataset.Row(new File(testFilePath + filenameTwo));
        assertTrue(row1.compareTo(row2) < 0);
        assertTrue(row2.compareTo(row1) > 0);
    }

    @Test
    void rowCompareToSameCatIDRecordVocalDifferentFile() {
        Dataset.Row row1 = new Dataset.Row(new File(testFilePath + filenameOne));
        Dataset.Row row2 = new Dataset.Row(new File(testFilePath + filenameOne));
        row2.recordingSessionVocalCounter = 101;
        assertEquals(0, row1.compareTo(row2));
        assertEquals(0, row2.compareTo(row1));
    }
}