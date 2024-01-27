package student;

public class Main {

    public static final int TIMES = 100;
    public static final int RANDOM_MEOWS = 100;


    public static void main(String[] args) {
        String path = "data/dataset/dataset";
        timeAddEachToFront(path);
        timeAddEachToBack(path);
        timeAccessRandomElements(path);
        timeSort(path);
    }

    public static void timeAddEachToFront(String path) {
        LinkedListDataset ll = new LinkedListDataset();
        ArrayListDataset al = new ArrayListDataset();
        System.out.println("Add each to front");
        printAverageTime(
                () -> ll.addEachToFront(path),
                () -> al.addEachToFront(path)
        );
        System.out.println();
    }

    public static void timeAddEachToBack(String path) {
        LinkedListDataset ll = new LinkedListDataset();
        ArrayListDataset al = new ArrayListDataset();
        System.out.println("Add each to back");
        printAverageTime(
                () -> ll.addEachToBack(path),
                () -> al.addEachToBack(path)
        );
        System.out.println();
    }

    public static void timeAccessRandomElements(String path) {
        LinkedListDataset ll = new LinkedListDataset();
        ArrayListDataset al = new ArrayListDataset();
        ll.addEachToBack(path);
        al.addEachToBack(path);
        System.out.println("Access random elements");
        printAverageTime(
                () -> getRandomMeows(RANDOM_MEOWS, ll, false),
                () -> getRandomMeows(RANDOM_MEOWS, ll, false)
        );
        System.out.println();
    }

    public static void timeSort(String path) {
        LinkedListDataset ll = new LinkedListDataset();
        ArrayListDataset al = new ArrayListDataset();
        ll.addEachToBack(path);
        al.addEachToBack(path);
        System.out.println("Sort");
        printAverageTime(
                ll::sortDataset,
                al::sortDataset
        );
        System.out.println();
    }

    /**
     * Randomly selects rows from given dataset, and optionally plays them
     *
     * @param num      the number of random rows to select
     * @param dataset  the dataset from which to select them
     * @param playThem whether to play the selected audio clip meows
     */
    public static void getRandomMeows(int num, Dataset dataset, boolean playThem) {
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
    public static void printAverageTime(Runnable linkedListFunc, Runnable arrayListFunc) {
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
    public static long averageTime(int times, Runnable func) {
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
    public static long timeMethod(Runnable func) {
        long before = System.nanoTime();
        func.run();
        long after = System.nanoTime();
        return after - before;
    }
}