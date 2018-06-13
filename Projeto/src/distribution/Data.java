package distribution;

import java.io.Serializable;

public class Data implements Serializable {
	static final long serialVersionUID = 1L;
	private String type;
	private String dataInfo;
	private long time;
	
	public Data(String type, String dataInfo) {
		this.type = type;
		this.dataInfo = dataInfo;
		this.time = System.currentTimeMillis();
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
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
