package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 회원가입
    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into user (user_id, user_pwd, user_phone, user_nickname, user_profile) VALUES (?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUser_id(), postUserReq.getUser_pwd(),postUserReq.getUser_phone(), postUserReq.getUser_nickname(), postUserReq.getUser_profile()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    //로그인
    public String loginUser(String user_id){
        String loginUserQuery = "select user_pwd from user where user_id=?";
        String loginUserParams = user_id;
        return this.jdbcTemplate.queryForObject(loginUserQuery,String.class,loginUserParams);
    }


    //비밀번호 변경
    public int modifyPwd(PatchPwdReq patchPwdReq){
        String modifyPwdQuery = "update user set user_pwd = ? where user_id = ? ";
        Object[] modifyPwdParams = new Object[]{patchPwdReq.getUser_id(), patchPwdReq.getUser_modify_pwd()};
        this.jdbcTemplate.update(modifyPwdQuery, modifyPwdParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    //닉네임 변경
    public int modifyNickname(PatchNicknameReq patchNicknameReq){
        String modifyNicknameQuery = "update user set user_nickname = ? where user_id = ? ";
        Object[] modifyNicknameParams = new Object[]{patchNicknameReq.getUser_modify_nickname(), patchNicknameReq.getUser_id()};

        return this.jdbcTemplate.update(modifyNicknameQuery,modifyNicknameParams);
    }

    //프론필 변경
    public int modifyProfile(PatchProfileReq patchProfileReq){
        String modifyProfileQuery = "update user set user_profile = ? where user_id = ? ";
        Object[] modifyProfileParams = new Object[]{patchProfileReq.getUser_modify_profile(), patchProfileReq.getUser_id()};

        return this.jdbcTemplate.update(modifyProfileQuery,modifyProfileParams);
    }

    //이메일 존재 확인
    public int checkEmail(String user_id){
        String checkEmailQuery = "select exists(select user_id from user where user_id = ?)";
        String checkEmailParams = user_id;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    //닉네임 존재 확인
    public int checkNickname(String user_nickname){
        String checkNicknameQuery = "select exists(select user_nickname from user where user_nickname = ?)";
        String checkNicknameParams = user_nickname;
        return this.jdbcTemplate.queryForObject(checkNicknameQuery,
                int.class,
                checkNicknameParams);
    }




}
