public interface List<T> {
    boolean add(T item);
    T get(int idx);
    void add(int idx, T item) throws Exception;
    T remove(int idx) throws Exception;
    int size();
}
