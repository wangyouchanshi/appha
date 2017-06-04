package com.baidu.entity;

public class User {
	
	private Integer uid;
	
    private String name;
	
	private double price;
	
    private String url;
    
    public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", price=" + price + ", url=" + url + "]";
	}





}
