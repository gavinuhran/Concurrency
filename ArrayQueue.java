/**
 * This ArrayQueue object represents a Queue ADT implemented as
 * an array using the QueueInterface
 * 
 * @author Tom Bredemeier
 * @version November 4, 2012
 */
public class ArrayQueue<T> implements QueueInterface<T>
{
    private int size;
    private int front;
    private int rear;
    private T[] queue;
    
    @SuppressWarnings("unchecked")
    public ArrayQueue()
    {
        queue = (T[]) new Object[4];
        size = 0;
        front = 0;
        rear = queue.length - 1;
        
    }

    // returns the logical size of the stack
    public int size()    
    {
        return size;
    }
    
    // tests if this stack is empty
    public boolean empty()
    {
        return (size == 0);
    }
    
    // adds an item into the front of this queue
    public T add(T item)
    {
        checkSize();
        rear = (rear + 1) % queue.length;
        queue[rear] = item;
        size ++;
        return item;
    }
    // looks at the object at the front of this queue
    // without removing it from the queue
    public T peek()
    {
        if (size < 1)
            throw new QueueUnderflowException();
        return queue[front];
    }
    
    // removes the object at the top of this stack 
    // and returns that object as the value of this function
    public T remove()
    {
        if(size < 1)
            throw new QueueUnderflowException();
        T result = peek();
        queue[front] = null;
        front = (front + 1) % queue.length;
        size --;
        checkSize();
        return result;
    }
        
    // removes all of the elements from this stack
    public void clear()
    {
        while(!empty())
            remove();
    }
    
    @SuppressWarnings("unchecked")
    private void checkSize()
    {
        int newSize;
        if(size == queue.length)
            newSize = queue.length * 2;   // double the array size
        else if(size < queue.length / 4)
            newSize = queue.length / 2;   // halve the array size
        else
            return;                       // leave the array alone
        T[] temp = (T[]) new Object[newSize];
        for(int i = 0; i < size; i++)
        {
            temp[i] = queue[front];       // copy over each element
            front = (front + 1) % queue.length;
        }
        queue = temp;                     // reassign the variable
        front = 0;
        rear = size - 1;
    }


}
