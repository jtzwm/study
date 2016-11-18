package com.zhuwm.h5.po;

public class Node {

	private String nodeCode;
	private String nextUrl;
	private int allowRedo;

	public Node(String nodeCode, String nextUrl, int allowRedo) {
		this.nodeCode = nodeCode;
		this.nextUrl = nextUrl;
		this.allowRedo = allowRedo;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public int getAllowRedo() {
		return allowRedo;
	}

	public void setAllowRedo(int allowRedo) {
		this.allowRedo = allowRedo;
	}

}
