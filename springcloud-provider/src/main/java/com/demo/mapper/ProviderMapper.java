package com.demo.mapper;

import com.demo.model.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProviderMapper {

    List<Users> findUserByLikeName(String name);


}
