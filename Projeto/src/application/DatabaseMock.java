package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import distribution.Data;

public class DatabaseMock {
	private static final Object dataLock = new Object();
	static Map<String, ArrayList<Data>> data = new HashMap<String, ArrayList<Data>>();
	
	public void add(Data info) {
		synchronized(dataLock) {
			if(!data.containsKey(info.getType())) {
				data.put(info.getType(), new ArrayList<Data>());
			}
			data.get(info.getType()).add(info);
		}
	}
	
	public ArrayList<Data> getData(String type) {
		synchronized(dataLock) {
			return data.get(type);
		}
	}
}
