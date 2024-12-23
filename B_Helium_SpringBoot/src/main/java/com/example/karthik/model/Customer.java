package com.example.karthik.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Component
@Entity
public class Customer {
	@Id
	private int cid;
	private String  cname;
	private int ctable;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getTable() {
		return ctable;
	}
	public void setTable(int table) {
		this.ctable = table;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", table=" + ctable + "]";
	}
	public Customer() {
		
	}
	
	
	

}
