public class ArrayList<T> implements List<T> {
    int size;
    T [] arr;

    public ArrayList() {
        arr = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public boolean add(T item) {
        if (arr[arr.length-1]!=null) {
            arr[size + 1] = item;
            size++;
        }
        if (arr[arr.length-1]== null){
            grow();
            arr[size+1] = item;
            size++;
        }
        return true;
    }

    @Override
    public T get(int idx) {
        return arr[idx];
    }

    @Override
    public void add(int idx, T item) throws Exception {
        if (idx<0 || idx>size)
            throw new Exception("Out of bounds.");
    }


    @Override
    public T remove(int idx) throws Exception {
        T rem = arr[idx];
        if (idx<0 || idx==size) {
            throw new Exception("Out of bounds.");
        } else {
            for (int i = idx + 1; i < size; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
            return rem;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        T[] arr_temp = (T[]) new Object[arr.length*2];
        for (int i=0; i<arr.length; i++){
            arr_temp[i] = arr[i];
        }
        arr = arr_temp;
    }

    public static void main(String [] args) throws Exception {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("a");
        arr.add("b");
        arr.remove(1);
        arr.add("c");
        arr.add("d");
    }
}
