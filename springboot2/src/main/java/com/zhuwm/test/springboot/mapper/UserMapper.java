package com.zhuwm.test.springboot.mapper;

import com.zhuwm.test.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    User Sel(int id);
}
