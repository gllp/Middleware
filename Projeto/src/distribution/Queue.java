package distribution;

import java.util.HashSet;
import java.util.Set;

public class Queue {
	private Set<Message> queue =  new HashSet<Message>();
	
	public Queue() {

	}
	
	public void enqueue(Message msg){
		queue.add(msg);
	}
	
	public void dequeue(Message msg) {
		queue.remove(msg);
	}
	
	public int queueSize() {
		return queue.size();
	}
}
