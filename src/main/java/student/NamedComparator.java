package student;

import java.util.Comparator;

public class NamedComparator<T> {
    private String name;
    private Comparator<T> comparator;

    public NamedComparator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }
}
