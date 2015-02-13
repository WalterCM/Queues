import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
    private Node pre;
    private Node post;
    private int  N = 0;

    private class Node
    {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque()
    {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }

    public boolean isEmpty()    { return pre.next == post;  }

    public int size()           { return N;                 }

    public void addFirst(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node first = new Node();
        Node oldfirst = pre.next;
        pre.next = first;
        first.prev = pre;
        first.next = oldfirst;
        oldfirst.prev = first;

        first.item = item;
        N++;
    }

    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node last = new Node();
        Node oldlast = post.prev;
        post.prev = last;
        last.next = post;
        last.prev = oldlast;
        oldlast.next = last;

        last.item = item;
        N++;
    }

    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Node first = pre.next;
        Item item = first.item;
        pre.next = first.next;
        first.next.prev = pre;
        first = null;
        N--;
        return item;
    }

    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Node last = post.prev;
        Item item = last.item;
        post.prev = last.prev;
        last.prev.next = post;
        last = null;
        N--;
        return item;

    }

    public Iterator<Item> iterator()    { return new ListIterator(); }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = pre.next;

        public boolean hasNext()    { return current != post; }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args)
    {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        d.removeLast();
        d.removeFirst();
        d.removeFirst();

        Iterator<Integer> it = d.iterator();
        while (it.hasNext())
            StdOut.println(it.next());

        StdOut.println(d.isEmpty());
        d.removeFirst();
        d.removeFirst();
        StdOut.println(d.isEmpty());
    }
}
