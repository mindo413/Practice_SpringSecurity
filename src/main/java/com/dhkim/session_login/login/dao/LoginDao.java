package com.dhkim.session_login.login.dao;

import com.dhkim.session_login.login.domain.Login;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {

    @Autowired
    SqlSession sqlSession;

    public Login selectUserById(String sqlId, String userId){

        return sqlSession.selectOne(sqlId, userId);
    }
}
