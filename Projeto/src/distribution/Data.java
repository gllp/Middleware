package distribution;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
	static final long serialVersionUID = 1L;
	private String type;
	private String dataInfo;
	private ArrayList<Long> time;
	
	public Data(String type, String dataInfo) {
		this.type = type;
		this.dataInfo = dataInfo;
		this.time = new ArrayList<>();
		this.time.add(System.currentTimeMillis());
	}

	public ArrayList<Long> getTime() {
		return time;
	}

	public void addTime() {
		this.time.add(System.currentTimeMillis());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(String dataInfo) {
		this.dataInfo = dataInfo;
	}
}
