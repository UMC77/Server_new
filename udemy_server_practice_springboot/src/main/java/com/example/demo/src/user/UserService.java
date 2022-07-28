package com.example.demo.src.user;


import com.example.demo.config.BaseException;

import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

    //회원가입
    public PostUserRes creatUser(PostUserReq postUserReq) throws BaseException {
        //이메일 중복 확인
        if(userProvider.checkEmail(postUserReq.getUser_id()) == 1)
            throw new BaseException(POST_USERS_EXISTS_EMAIL);

        //닉네임 중복 확인
        if(userProvider.checkNickname(postUserReq.getUser_nickname()) == 1)
            throw new BaseException(POST_USERS_EXISTS_NICKNAME);

        String password;

        try {
            //비밀번호 암호화
            password = new SHA256().encrypt(postUserReq.getUser_pwd());
            postUserReq.setUser_pwd(password);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            int userIdx = userDao.createUser(postUserReq);

            //jwt 발급
            String jwt = jwtService.createJwt(userIdx);
            return new PostUserRes(jwt, userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //로그인
    public void loginUser(GetUserReq getUserRes) throws BaseException {
        try {
            String password;

            password = new SHA256().encrypt(getUserRes.getUser_pwd());

            String check = userDao.loginUser(getUserRes.getUser_id());

            if (!password.equals(check))
                throw new BaseException(USERS_ERROR_USER_PASSWORD);
        } catch (Exception exception)
            throw new BaseException(DATABASE_ERROR);
    }

    //비밀번호 변경
    public PatchPwdRes modifyPwd(PatchPwdReq patchPwdReq) throws BaseException {
        //아이디의 존재 확인
        if(userProvider.checkPwd(patchPwdReq.getUser_id()) == 0)
            throw new BaseException(USERS_EMPTY_USER_ID);

        try {
            //비밀번호 암호화
            String password;
            password = new SHA256().encrypt(patchPwdReq.getUser_modify_pwd());
            patchPwdReq.setUser_modify_pwd(password);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            int userIdx = userDao.modifyPwd(patchPwdReq);

            //jwt 발급
            String jwt = jwtService.createJwt(userIdx);
            return new PatchPwdRes(jwt, userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //닉네임 변경
    public void modifyNickname(PatchNicknameReq patchNicknameReq) throws BaseException {
        //아이디의 존재 확인
        if(userProvider.checkEmail(patchNicknameReq.getUser_id()) == 0)
            throw new BaseException(USERS_EMPTY_USER_ID);

        try {
            userDao.modifyNickname(patchNicknameReq.getUser_modify_nickname());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    //프로필 변경
    public void modifyProfile(PatchProfileReq patchProfileReq) throws BaseException {
        //아이디의 존재 확인
        if(userProvider.checkEmail(patchProfileReq.getUser_id()) == 0)
            throw new BaseException(USERS_EMPTY_USER_ID);

        try {
            userDao.modifyProfile(patchProfileReq.getUser_modify_profile());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



    /*
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        // 이메일 중복 확인
        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());  postUserReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int userIdx = userDao.createUser(postUserReq);
            //jwt 발급.
            // TODO: jwt는 다음주차에서 배울 내용입니다!
            String jwt = jwtService.createJwt(userIdx);
            return new PostUserRes(jwt,userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        try{
            int result = userDao.modifyUserName(patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
   */
}
