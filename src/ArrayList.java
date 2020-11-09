// ArrayList implementation
public class ArrayList<T> implements List<T> {
    int size;
    T [] arr;

    // initializes ArrayList with 10 empty items
    public ArrayList() {
        arr = (T[]) new Object[10];
        size = 0;
    }

    // method for adding a planet to given index
    @Override
    public boolean add(T item) {
        // if the last spot in the array is unoccupied, item is simply added to the first empty spot
        if (arr[arr.length-1]!=null) {
            arr[size + 1] = item;
            size++;
        }
        // if array is full, number of spots is doubled and item is added
        if (arr[arr.length-1]== null){
            grow();
            arr[size+1] = item;
            size++;
        }
        return true;
    }

    // returns item at a given position
    @Override
    public T get(int idx) {
        //if item is within the bounds of the array list, it is returned others null is returned
        if (idx >= 0 && idx<=size){
            return arr[idx+1];
        } else {
            return null;
        }

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
            // if idx of item to be removed is not the last item in the array, all items after the one to be removed
            // are shifted back one spot and size is decremented (no need to remove the extra item at the end as it
            // will be overwritten and is no longer within the bounds of the get() method.
            if (idx<size) {
                for (int i = idx + 1; i < size; i++) {
                    arr[i] = arr[i + 1];
                }
            }
            // if item to be removed is at the end, size is simply decremented
            size--;
            return rem;
        }
    }

    // size getter
    @Override
    public int size() {
        return size;
    }

    // creates new array with double the capacity and copies the old array into it then replaces the old array
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
