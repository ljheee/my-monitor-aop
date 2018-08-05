package com.ljheee.tk.service;

import com.ljheee.tk.entity.UserInfo;
import com.ljheee.tk.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by lijianhua04 on 2018/8/4.
 */
@Service
public class UserService {


    @Autowired
    private UserInfoMapper userInfoMapper;


    public List<UserInfo> selectAll() {
        return userInfoMapper.selectAll();
    }


    public List<UserInfo> findUserById(Integer id) {

        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("id",id);
        return userInfoMapper.selectByExample(example);
    }
}
