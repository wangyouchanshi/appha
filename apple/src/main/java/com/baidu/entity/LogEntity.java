package com.baidu.entity;

public class LogEntity {

	private Integer id;
	private String ip;
	private String cityname;
	private String browser;
	private String datea;
	public String getDatea() {
		return datea;
	}

	public void setDatea(String datea) {
		this.datea = datea;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public LogEntity(String ip, String cityname, String browser) {
		super();
		this.ip = ip;
		this.cityname = cityname;
		this.browser = browser;
	}

	public LogEntity(String ip, String cityname, String browser, String datea) {
		super();
		this.ip = ip;
		this.cityname = cityname;
		this.browser = browser;
		this.datea = datea;
	}

	public LogEntity() {
		super();
	}
	
	
	
}
