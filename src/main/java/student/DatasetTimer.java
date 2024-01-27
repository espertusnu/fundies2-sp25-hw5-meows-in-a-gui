package student;

public class DatasetTimer {
    public static final String PATH = "data/dataset/dataset";

    public static final int TIMES = 100;
    public static final int RANDOM_MEOWS = 100;
    private String path;

    private LinkedListDataset llDataset;
    private ArrayListDataset alDataset;

    public DatasetTimer(String path) {
        this.path = path;
    }


    public static void main(String[] args) {
        DatasetTimer timer = new DatasetTimer(PATH);
        timer.timeAddEachToFront();
        timer.timeAddEachToBack();
        timer.timeAccessRandomElements();
        timer.timeSort();
    }

    private void initializeDatasets() {
        llDataset = new LinkedListDataset();
        alDataset = new ArrayListDataset();
    }

    private void loadDatasetsIfNull() {
        if (llDataset == null || alDataset == null) {
            initializeDatasets();
            llDataset.addEachToBack(path);
            alDataset.addEachToBack(path);
        }
    }

    private void timeMethod(boolean initialize, String title, Runnable llRunnable, Runnable alRunnable) {
        if (initialize) {
            initializeDatasets();
        }
        System.out.println(title);
        printAverageTime(llRunnable, alRunnable);
        System.out.println();
    }

    /**
     * Times the addEachToFront method for LinkedListDataset and ArrayListDataset.
     * Prints the nanoseconds for each.
     */
    public void timeAddEachToFront() {
        timeMethod(
                true,
                "Add each to front",
                () -> llDataset.addEachToFront(path),
                () -> alDataset.addEachToFront(path)
        );
    }

    /**
     * Times the addEachToBack method for LinkedListDataset and ArrayListDataset.
     * Prints the nanoseconds for each.
     */
    public void timeAddEachToBack() {
        timeMethod(
                true,
                "Add each to back",
                () -> llDataset.addEachToBack(path),
                () -> alDataset.addEachToBack(path)
        );
    }

    /**
     * Times the getRandomRow method for LinkedListDataset and ArrayListDataset.
     * Prints the nanoseconds for each.
     */
    public void timeAccessRandomElements() {
        loadDatasetsIfNull();
        timeMethod(
                false,
                "Access random elements",
                () -> getRandomMeows(RANDOM_MEOWS, llDataset, false),
                () -> getRandomMeows(RANDOM_MEOWS, alDataset, false)
        );
    }

    /**
     * Times the sort method for LinkedListDataset and ArrayListDataset.
     * Prints the nanoseconds for each.
     */
    public void timeSort() {
        loadDatasetsIfNull();
        timeMethod(
                false,
                "Sort",
                llDataset::sortDataset,
                alDataset::sortDataset
        );
    }

    /**
     * Randomly selects rows from given dataset, and optionally plays them
     *
     * @param num      the number of random rows to select
     * @param dataset  the dataset from which to select them
     * @param playThem whether to play the selected audio clip meows
     */
    public void getRandomMeows(int num, Dataset dataset, boolean playThem) {
        for (int i = 0; i < num; i++) {
            Dataset.Row meow = dataset.getRandomRow();
            if (playThem) {
                meow.play();
            }
        }
    }

    /**
     * Prints the average number of nanoseconds used to run each passed function
     *
     * @param linkedListFunc a function that uses a LinkedListDataset
     * @param arrayListFunc  a function that uses an ArrayListDataset
     */
    public void printAverageTime(Runnable linkedListFunc, Runnable arrayListFunc) {
        long linkedListTime = averageTime(TIMES, linkedListFunc);
        System.out.printf("Nanoseconds for LinkedListDataset: %d\n", linkedListTime);
        long arrayListTime = averageTime(TIMES, arrayListFunc);
        System.out.printf("Nanoseconds for ArrayListDataset:  %d", arrayListTime);
        System.out.printf(" (%d nanoseconds more)\n", arrayListTime - linkedListTime);
    }

    /**
     * @param times the number of times to run the function
     * @param func  a function with no parameters and a void return
     * @return the average number of nanoseconds it took to run the passed function,
     * after running it the passed number of times
     */
    public long averageTime(int times, Runnable func) {
        long totalTime = 0;
        for (int i = 0; i < times; i++) {
            totalTime += timeMethod(func);
        }
        return totalTime / times;
    }

    /**
     * @param func a function with no parameters and a void return
     * @return the number of nanoseconds it took to run the passed function
     */
    public long timeMethod(Runnable func) {
        long before = System.nanoTime();
        func.run();
        long after = System.nanoTime();
        return after - before;
    }
}