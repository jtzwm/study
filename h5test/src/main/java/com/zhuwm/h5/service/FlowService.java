package com.zhuwm.h5.service;

import org.springframework.stereotype.Service;

import com.zhuwm.h5.dao.BizFlowControlDAO;
import com.zhuwm.h5.po.BizFlowNode;
import com.zhuwm.h5.po.Flow;
import com.zhuwm.h5.po.Node;

@Service
public class FlowService {
	BizFlowControlDAO bizFlowControlDAO = new BizFlowControlDAO();

	//通过bizCode，获取此业务数据的流程节点
	public String getNextCodeUrl(String bizCode,String flowType) {
		
		//获取这个bizCode在流程控制器中所处的流程
		String  nodeCode=getFlowControl(bizCode,flowType);
		//如果流程表中没有记录，则从第一个流程开始
		if(nodeCode==null){
			String firstNodeCode=getFirstNode(flowType);
			Node firstNode=bizFlowControlDAO.getNode(firstNodeCode);
			return firstNode.getNextUrl();
		}

		
		//到流程控制器中去查，下一个节点
		String nextURL=getNextNode(flowType,nodeCode);
		
		return nextURL;
	}

	private String getNextNode(String flowCode, String nodeCode) {
		//先根据flowCode和nodeCode找flow中的流程设置
		Flow flow=bizFlowControlDAO.getFlow(flowCode,nodeCode);
		Node nextNode=bizFlowControlDAO.getNode(flow.getNextNode());
		return nextNode.getNextUrl();
		
	}

	
	private String getFlowControl(String bizCode,String flowType) {
		String flowNode=bizFlowControlDAO.getLastNode(bizCode,flowType);

		return flowNode;
	}
	
	private String getFirstNode(String flowType){
		return bizFlowControlDAO.getFirstNodeOfFlow(flowType);
	}
	

}
