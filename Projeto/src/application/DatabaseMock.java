package application;

import java.util.ArrayList;

public class DatabaseMock {
	private static final Object dataLock = new Object();
	private static ArrayList<Object> data = new ArrayList<>();
	
	public void add(Object obj) {
		synchronized(dataLock) {
			data.add(obj);
		}
	}
	
	public ArrayList<Object> getData() {
		synchronized(dataLock) {
			return data;
		}
	}
}
