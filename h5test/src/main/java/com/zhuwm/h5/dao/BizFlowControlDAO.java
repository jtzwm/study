package com.zhuwm.h5.dao;

import java.util.HashMap;
import java.util.Map;

import com.zhuwm.h5.po.BizFlowNode;
import com.zhuwm.h5.po.Flow;
import com.zhuwm.h5.po.Node;

public class BizFlowControlDAO {

	private static Map<String, BizFlowNode> bizData = null ;
	private static Map<String, Flow> flowData = null;
	private static Map<String, Node> nodeData = null;
	private static Map<String,String> firstNodeOfFlow =null;
	static{
		init();
	}

	public String getLastNode(String bizCode,String flowType) {
		
		for(BizFlowNode value:bizData.values()){
			if(flowType.equals(value.getFlowCode()) && bizCode.equals(value.getBizCode())){
				return value.getNodeCode();
			}
		}
		return null;
	}
	
	public String getFirstNodeOfFlow(String flowCode){
			return firstNodeOfFlow.get(flowCode);
	}

	public Flow getFlow(String flowCode,String nodeCode) {
		return flowData.get(flowCode+":"+nodeCode);
	}

	public Node getNode(String nodeCode) {
		return nodeData.get(nodeCode);

	}



	private static void init() {

		bizData=new HashMap<String, BizFlowNode>();		
		BizFlowNode biz1 = new BizFlowNode("A", "1", "00001");
		bizData.put("00001", biz1);
		
		flowData = new HashMap<String, Flow>();
		Flow flow1 = new Flow("A","1","2",1,0);
		Flow flow2 = new Flow("A","2","3",0,0);
		Flow flow3 = new Flow("A","3","4",0,1);
		flowData.put("A:1", flow1);
		flowData.put("A:2", flow2);
		flowData.put("A:3", flow3);
		
		firstNodeOfFlow = new HashMap<String,String>();
		firstNodeOfFlow.put("A", "1");
		
		nodeData = new HashMap<String, Node>();
		Node node1 = new Node("1", "info.do", 1);
		Node node2 = new Node("2", "appoint.do", 1);
		Node node3 = new Node("3", "video.do", 1);
		Node node4 = new Node("4", "aduit.do", 1);
		nodeData.put("1", node1);
		nodeData.put("2", node2);
		nodeData.put("3", node3);
		nodeData.put("4", node4);
		

	}

}
