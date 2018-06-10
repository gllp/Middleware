package distribution;

import java.io.IOException;

public interface IQueueManager {
	public void send(Object msg) throws IOException, InterruptedException;
	
	public Object receive() throws IOException, InterruptedException, ClassNotFoundException;
}
