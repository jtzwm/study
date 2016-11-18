package com.zhuwm.h5.po;


public class Flow {
	
	private String flowCode;
	private String node;
	private String nextNode;
	private int isFirst;
	private int isLast;
	
	public Flow(String flowCode,String node,String nextNode){
		this.flowCode=flowCode;
		this.node=node;
		this.nextNode=nextNode;
	}
	
	public Flow(String flowCode,String node,String nextNode,int isFirst,int isLast){
		this(flowCode,node,nextNode);
		this.isFirst=isFirst;
		this.isLast=isLast;
	}
	
	
	public String getFlowCode() {
		return flowCode;
	}

	
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getNode() {
		return node;
	}
	
	public void setNode(String node) {
		this.node = node;
	}
	
	public String getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(String nextNode) {
		this.nextNode = nextNode;
	}

}
