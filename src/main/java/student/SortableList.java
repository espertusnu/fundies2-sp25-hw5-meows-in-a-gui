package student;

import java.util.List;

public class SortableList<T extends Playable> {
    private List<T> data;

    private List<NamedComparator<T>> comparators;

    public SortableList(List<T> data, List<NamedComparator<T>> comparators) {
        this.data = data;
        this.comparators = comparators;
    }

    public List<T> getData() {
        return data;
    }

    public List<NamedComparator<T>> getComparators() {
        return comparators;
    }
}
