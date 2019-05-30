public class IncreaseUseArray implements Runnable
{
    private SyncCounter c;
    private QueueInterface<Integer> q;

    public IncreaseUseArray (SyncCounter c, QueueInterface<Integer> q)
    {
        this.c = c;  
        this.q = q;
    }

    public void run()
    {
        int hold;
        while (!q.empty())
        {
            hold = q.remove();
            for (int i = 1; i <= hold; i++)
                c.increment();
        }
    }
}