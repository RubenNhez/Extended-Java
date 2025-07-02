import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;

public class MyMiniList <T> implements MiniList<T> {

    T[] objectStore = (T[]) new Object[10];
    int variableSize = 0;

    @Override
    public void add(T element) {
        if (objectStore[objectStore.length-1] != null) {
            int tempArray =size();
            objectStore = Arrays.copyOf(objectStore,objectStore.length * 2);

            System.out.println("This is element: "+element);
            objectStore[tempArray] = element;
            variableSize++;
        }
        else {

            objectStore[size()] = element;
            variableSize++;
        }



    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index,objectStore.length);
        return objectStore[index];
    }

    @Override
    public int getIndex(T element) {
        for (int i = 0; i < size(); i++) {
            if (objectStore[i] == element) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void set(int index, T element) {
        System.out.println("Item at Index: "+index+" Is :"+objectStore[index]);
        if(objectStore[index] != null) {
            objectStore[index] = element;
        }
        else {
            System.out.println("Index doesn't exist");
        }

        System.out.println("Item at Index: "+index+" Is :"+objectStore[index]);

    }

    @Override
    public int size() {
        return variableSize;
    }

    @Override
    public T remove(int index) {
        T delete = objectStore[index];
        if (index > variableSize) {
            return null;
        }

        for (int i = index; i < objectStore.length-1; i++) {
            objectStore[i] = objectStore[i+1];
            if(objectStore[i] ==null) {
                break;
            }
//            System.out.println("Item was deleted");
        }
        return delete;

    }

    @Override
    public boolean remove(T element) {
        T  delete;

        if(getIndex(element) == -1){
            return false;
        }
        if(getIndex(element) == objectStore.length-1){
            objectStore[objectStore.length-1]  = null;
            return true;
        }

        for (int i = getIndex(element); i < objectStore.length-1; i++) {
//
            objectStore[i] = objectStore[i+1];
        }
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(objectStore,null);
    }
}
