package base;

import java.util.Iterator;

/**
 * @author galileo
 * @date 2019/7/11 9:35
 */
public class Queue<Item> implements Iterable<Item>{
    private int n;
    private Node first;
    private Node last;

    class Node{
        Item item;
        Node next;
    }

    public void enquene(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }else {
            oldLast.next = last;
        }
        n++;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        n--;
        return item;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }
}
