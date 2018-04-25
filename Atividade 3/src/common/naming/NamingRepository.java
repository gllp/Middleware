package common.naming;

import java.util.ArrayList;

public class NamingRepository {
	private ArrayList<NamingRecord> namingRecords = new ArrayList<NamingRecord>();
	
	public void add(NamingRecord namingRecord) {
		namingRecords.add(namingRecord);
	}
	
	public NamingRecord find(String serviceName) {
		for(NamingRecord record : namingRecords) {
			if(record.getServiceName().equals(serviceName)) {
				return record;
			}
		}
		return null;
	}
	
	public ArrayList<String> listServiceNames() {
		ArrayList<String> serviceNames = new ArrayList<String>();
		
		for(NamingRecord record : namingRecords) {
			serviceNames.add(record.getServiceName());
		}
		
		return serviceNames;
	}
}
