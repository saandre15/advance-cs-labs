public class ListStack implements Stack
{
    private java.util.LinkedList list;

    public ListStack()
    {
        list = new java.util.LinkedList();
    }
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    public void push(Object obj)
    {
        list.addFirst(obj);
    }
    public Object pop()
    {
        return list.removeFirst();
    }
    public Object peekTop()
    {
        return list.getFirst();
    }
}

