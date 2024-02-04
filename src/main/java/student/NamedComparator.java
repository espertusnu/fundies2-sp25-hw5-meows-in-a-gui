package student;

import java.util.Comparator;

public class NamedComparator<T> {
    private String name;
    private Comparator<T> comparator;

    public NamedComparator(String name, Comparator<T> comparator) {
        this.name = name;
        this.comparator = comparator;
    }

    public String getName() {
        return name;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }
}
