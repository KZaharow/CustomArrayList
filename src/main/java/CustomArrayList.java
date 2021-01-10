import java.util.*;
import java.util.function.UnaryOperator;

public class CustomArrayList<E> implements List<E> {

    private ArrayList a;

    //uses for define array default capacity
    private static final int DEFAULT_CAPACITY = 1;
    private static final int MAX_CAPACITY = 0x7FFFFFFF;


    private Object[] items;
    private int size;


    public CustomArrayList() {
        items = new Object[]{};
    }

    /**
     * The method adds input E e element at the collection end. Collection increases +1 item
     *
     * @param e - input object, type E
     * @return <tt>true</tt> if element has been added, else return CustomArrayException
     */
    @Override
    public boolean add(E e) {
        Object[] n = new Object[size + 1];
        System.arraycopy(items, 0, n, 0, size);
        n[size++] = e;
        items = n;
        return true;
    }

    /**
     * The method paste input E e element inside collection. Collection increases +1 item
     *
     * @param index   - target index where will be a new element inside collection
     * @param element - input object, type E
     */
    public void add(int index, E element) {
        checkSize(index);
        Object[] n = new Object[size + 1];
        System.arraycopy(items, 0, n, 0, index);
        System.arraycopy(items, index, n, index, size - index);
        size++;
        n[index] = element;
        items = n;
    }

    /**
     * The method returns target collection item by index
     *
     * @param index - target collection item index
     * @return E type object if exist. else return CustomArrayException
     */
    @Override
    public E get(int index) {
        checkSize(index);
        return (E) items[index];
    }

    /**
     * The method adds new collection to the current collection end.
     * New collection should be parametrized by similar target type
     *
     * @param c - inp. collection parametrized by similar target type
     * @return <tt>true</tt> if element has been added, else return CustomArrayException
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            add((E) arr[i]);
        }
        return true;
    }

    /**
     * The method inser new collection to the current collection.
     * New collection should be parametrized by similar target type
     *
     * @param index - target collection item index
     * @param c     - inserted collection
     * @return - <tt>true</tt> if collection has been added, else return CustomArrayException
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int os = size;
        int cs = c.size();
        int ns = os + cs;
        Object[] n = new Object[ns];
        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            add((E) arr[i]);
        }
        System.arraycopy(items, 0, n, 0, index);
        System.arraycopy(items, os, n, index, cs);
        System.arraycopy(items, index, n, ns - cs, ns - os);
        items = n;
        return true;
    }

    /**
     * The method returns <tt>true/false</tt> if inp. collection contains
     * all items which are in target collection
     * @param c
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        int counter = 0;
        int cs = c.size();
        Object[] arr = c.toArray();
        for (int i = 0; i < cs; i++) {
            if (indexOf(arr[i]) >= 0){
                counter++;
            }
        }
        return (counter == cs);
    }

    /**
     * The method returns <tt>true/false</tt> if collection contains
     * a specified item
     *
     * @param o - checking item
     * @return - <tt>true/false</tt>
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * The method change all collection objects ref to  <tt>null</tt>
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
    }

    /**
     * The method returns <tt>true/false</tt> if collection contains more the 1 item
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns Iterator<E> for future uses
     * @return - Iterator<E>
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>((E[]) items);
    }

    /**
     * The method return an index of last object in collection
     * if it present
     * @param o - checked object
     * @return - index of last item
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (items[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(items[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        // not impl
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // not impl
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        // not impl
    }

    /**
     * Delete a specified collection item by defined index
     * @param index - deleted item
     * @return - deleted object
     */
    @Override
    public E remove(int index) {
        checkSize(index);
        int ns = --size;
        Object[] n = new Object[ns];
        System.arraycopy(items, 0, n, 0, index);
        System.arraycopy(items, index + 1, n, index, ns - index);
        items = n;
        return (E) items[index];
    }

    /**
     * Delete a specified collection item by it type (first meet)
     * @param o - deleted object
     * @return - bool
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (items[index] == null) {
                    remove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(items[index])) {
                    remove(index);
                    return true;
                }
        }
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            remove(arr[i]);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public E set(int index, E element) {
        checkSize(index);
        E oldItem = (E) items[index];
        items[index] = element;
        return oldItem;
    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    /**
     * The method returns item quantity in curren collection object
     * @return int - size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The method convert current collection to arryay
     * @return  Object[]
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * Returns the index of the first specified item.
     * return -1 if this list does not contain the element.
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (items[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(items[i]))
                    return i;
        }
        return -1;
    }

    /**
     * The method returns a specified sub collection
     * from current collection.
     * @param fromIndex - start from index
     * @param toIndex - stop to index
     * @return List<E> - parametrized list object
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        checkSize(fromIndex);
        checkSize(toIndex);
        CustomArrayList<E> c = new CustomArrayList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            c.add((E) items[i]);
        }
        return c;
    }

    /**
     * tools method uses for check collection size
     * @param index - index for checking
     */
    private void checkSize(int index) {
        if (index >= size) {
            try {
                throw new CustomArrayException("OutOfRange");
            } catch (CustomArrayException e) {
                e.printStackTrace();
            }
        }
    }
}

class CustomArrayException extends Exception {
    private final String msg;

    public CustomArrayException(String msg) {
        this.msg = msg;
    }

    public void getMsg() {
        System.out.println(msg);
    }

}