import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] q;
    private int    N    = 0;

    public RandomizedQueue()    { q = (Item[]) new Object[2];   }

    public boolean isEmpty()    { return N == 0;                }

    public int size()           { return N;                     }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = q[i];
        q = copy;
    }

    private void swap(int i, int j) {
        Item temp = q[i];
        q[i] = q[j];
        q[j] = temp;
    }

    public void enqueue(Item item)
    {
        if (item == null)   throw new java.lang.NullPointerException();
        if (N == q.length)  resize(2 * q.length);
        q[N++] = item;
    }

    public Item dequeue()
    {
        if (isEmpty())      throw new java.util.NoSuchElementException();
        int rand =  StdRandom.uniform(N);
        Item item = q[rand];
        q[rand] = null;
        swap(N - 1, rand);
        N--;
        if (N > 0 && N == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Item sample()
    {
        if (isEmpty())      throw new java.util.NoSuchElementException();
        int rand = StdRandom.uniform(N);
        return q[rand];
    }

    public Iterator<Item> iterator()
    {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item>
    {
        private Item[] rand;
        private int   current = 0;

        public ArrayIterator()
        {
            rand = (Item[]) new Object[N];
            for (int i = 0; i < rand.length; i++) {
                rand[i] = q[i];
            }
            StdRandom.shuffle(rand);
        }

        public boolean hasNext() { return current != rand.length; }

        public void remove()
        { throw new java.lang.UnsupportedOperationException(); }

        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            
            Item item = rand[current++];
            return item;
        }
    }

    public static void main(String[] args)
    {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        int t = 1000000;
        for (int i = 0; i < t; i++) {
            r.enqueue(i);
        }

        Iterator<Integer> it = r.iterator();
        int i = 0;
        while (it.hasNext()) {
            StdOut.println(i++ + ": " + it.next());
        }
    }
}
