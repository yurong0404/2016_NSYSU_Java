import java.util.LinkedList;

public class QueueMachine {

	private LinkedList<Transaction> queue;
	
	public QueueMachine()
	{
		this.queue = new LinkedList<Transaction>();
	}
	
	public boolean isEmpty()
	{
		if(queue.peek()==null)
			return true;
		else
			return false;
	}
	public Transaction get()
	{
		return queue.removeFirst();
	}
	public void add(Transaction newTran)
	{
		queue.add(newTran);
	}
}
