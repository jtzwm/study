package com.zhuwm.mybatis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhuwm.mybatis.base.RedisFirstDAO;
import com.zhuwm.mybatis.entity.zrxtcs.Projects;
import com.zhuwm.mybatis.mapper.ProjectsMapper;

/**
 * 处理项目的DAO类，Projects要优先从Redis中取。
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author zhuweiming<br>
 * 开发时间: 2016年2月25日<br>
 */
@Component
public class ProjectsDAO extends RedisFirstDAO{
	@Autowired
	private ProjectsMapper projectsMapper;
	
	/**
	 * 获取
	 * @author zhuweiming
	 * @param projectID
	 * @return
	 * @throws Exception
	 */
	public Projects findProjects(long projectID)
			throws Exception {

		return (Projects)getSinglerObjectRedisFirst(String.valueOf(projectID));
		//return projectsMapper.selectByPrimaryKey((long)210160);
		
	}

	@Override
	protected Object getObjectFromDataBase() {
		// TODO Auto-generated method stub
		return projectsMapper.selectByPrimaryKey((long)210160);
	}
}
