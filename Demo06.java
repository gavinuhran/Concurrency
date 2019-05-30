public class Demo06
{
    public static void main(String[] args) throws InterruptedException
    {
        int LIMIT = 100;
        SyncCounter c = new SyncCounter();
        QueueInterface<Integer> q;
        q = new ArrayQueue<Integer>();

        for (int i = 1; i <= LIMIT; i++)
            q.add(i);

        Runnable r1 = new IncreaseUseArray(c, q);
        Runnable r2 = new IncreaseUseArray(c, q);
        Thread   t1 = new Thread(r1);
        Thread   t2 = new Thread(r2);

        t1.start();  t2.start();
        t1.join();   t2.join();

        System.out.println("Count is: " + c.getCount());
    }
}