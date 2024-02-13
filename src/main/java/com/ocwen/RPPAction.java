package com.ocwen;

import com.opensymphony.xwork2.ActionSupport;

public class RPPAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String activity;
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String execute()
	{
		return SUCCESS;
	}
}
