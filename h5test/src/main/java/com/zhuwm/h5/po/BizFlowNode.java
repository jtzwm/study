package com.zhuwm.h5.po;

public class BizFlowNode {

	// 流程控制器的code
	private String flowCode;
	// 流程控制器中，一个bizCode所处节点的code
	private String nodeCode;
	// 流程控制器中的bizCode，业务主键
	private String bizCode;
	
	public BizFlowNode(String flowCode,String nodeCode,String bizCode){		
		this.flowCode=flowCode;
		this.nodeCode=nodeCode;
		this.bizCode=bizCode;
	}
	
	public String getFlowCode() {
		return flowCode;
	}
	
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	
	public String getNodeCode() {
		return nodeCode;
	}
	
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
	public String getBizCode() {
		return bizCode;
	}
	
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

}
