public interface List<T> {
    // This is an interface for LinkedList and ArrayList so that polymorphism can
    // be used to simplify implementations for both data structures

    boolean add(T item);
    T get(int idx);
    void add(int idx, T item) throws Exception;
    T remove(int idx) throws Exception;
    int size();
}
