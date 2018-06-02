package distribution;

import java.util.ArrayList;

public class Queue {
	private ArrayList<Message> queue =  new ArrayList<Message>();
	
	public Queue() {

	}
	
	public void enqueue(Message msg){
		queue.add(msg);
	}
	
	public Message dequeue() {
		return queue.remove(queue.size()-1);
	}
	
	public int queueSize() {
		return queue.size();
	}
}
