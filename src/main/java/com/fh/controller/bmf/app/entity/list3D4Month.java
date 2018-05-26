package com.fh.controller.bmf.app.entity;

import java.util.List;

import com.fh.entity.bmf.member.Member3DMatchMethod;

public class list3D4Month {
	private String month;
	private List<Member3DMatchMethod> member3DMatchMethodList;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<Member3DMatchMethod> getMember3DMatchMethodList() {
		return member3DMatchMethodList;
	}
	public void setMember3DMatchMethodList(List<Member3DMatchMethod> member3dMatchMethodList) {
		member3DMatchMethodList = member3dMatchMethodList;
	}
	
}
