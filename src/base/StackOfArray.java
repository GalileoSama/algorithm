package base;

import java.util.Iterator;

/**
 * @author galileo
 * @date 2019/7/10 14:56
 */
public class StackOfArray<Item> implements Iterable<Item>{
    private Item[] a;
    private int n;

    public StackOfArray(int cap) {
        n = 0;
        a = (Item[]) new Object[cap];
    }

    private void resize(int cap){
        Item[] temp = (Item[]) new Object[cap];
        if (n >= 0) {
            System.arraycopy(a, 0, temp, 0, n);
        }
        a = temp;
    }

    public void push(Item item){
        if (n > a.length/2){
            resize(2*a.length);
        }
        a[n++] = item;
    }

    public Item pop(){
        Item item = a[--n];
        //避免对象游离
        a[n] = null;
        if (n > 0 && n < a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }
}
