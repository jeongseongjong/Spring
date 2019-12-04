package com.biz.practice.domain;

public class UserDTO {

	private String u_id;
	private String u_pw;
	private String u_name;
	private long u_num;
	private String u_tel;
	private String u_add;
	
	
	
	
	public UserDTO(String u_id, String u_pw, String u_name, long u_num, String u_tel, String u_add) {
		super();
		this.u_id = u_id;
		this.u_pw = u_pw;
		this.u_name = u_name;
		this.u_num = u_num;
		this.u_tel = u_tel;
		this.u_add = u_add;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public long getU_num() {
		return u_num;
	}
	public void setU_num(long u_num) {
		this.u_num = u_num;
	}
	public String getU_tel() {
		return u_tel;
	}
	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}
	public String getU_add() {
		return u_add;
	}
	public void setU_add(String u_add) {
		this.u_add = u_add;
	}
	@Override
	public String toString() {
		return "UserDTO [u_id=" + u_id + ", u_pw=" + u_pw + ", u_name=" + u_name + ", u_num=" + u_num + ", u_tel="
				+ u_tel + ", u_add=" + u_add + "]";
	}
	
	
}
