package com.chemao.log.monitor.domain;

public class MonitorConfigDO {
	private String logName;
	private String keyword;
	private String userId;
	private String namespace;
	private Boolean run; 
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public Boolean getRun() {
		return run;
	}
	public void setRun(Boolean run) {
		this.run = run;
	}
}
