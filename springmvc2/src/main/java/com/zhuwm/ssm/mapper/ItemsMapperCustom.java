package com.zhuwm.ssm.mapper;

import java.util.List;

import com.zhuwm.ssm.po.ItemsCustom;
import com.zhuwm.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}