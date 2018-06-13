package sensors;

public class Server {
	public static void main(String[] args) {
		int sensorsQty = 2;
		
		for(int i = 0; i < sensorsQty; i++) {
			new Thread(new Sensor("sensor"+i)).start();
		}
	}
}
