public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> r = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
            r.enqueue(StdIn.readString());

        while (k-- > 0) {
            StdOut.println(r.dequeue());
        }
    }
}
