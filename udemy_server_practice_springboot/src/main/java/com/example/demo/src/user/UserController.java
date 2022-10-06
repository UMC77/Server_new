package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;


    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }


    //회원가입
    @ResponseBody
    @PostMapping("/signup")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        System.out.println("signup 실행");
        //이메일 공백 체크
        if(postUserReq.getUser_id() == null)
            return new BaseResponse<>(APP_POST_USERS_EMPTY_EMAIL);

        //이메일 형식 체크
        if(!isRegexEmail(postUserReq.getUser_id()))
                return new BaseResponse<>(APP_POST_USERS_INVALID_EMAIL);

        //비밀번호 공백 체크
        if(postUserReq.getUser_pwd() == null)
            return new BaseResponse<>(APP_POST_USERS_EMPTY_PASSWORD);

        //닉네임 공백 체크
        if(postUserReq.getUser_nickname() == null)
            return new BaseResponse<>(APP_POST_USERS_EMPTY_NICKNAME);

        //전화번호 공백 체크
        if(postUserReq.getUser_phone() == null)
            return new BaseResponse<>(APP_POST_USERS_EMPTY_PHONENUM);

        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //로그인
    @ResponseBody
    @GetMapping("/login")
    public BaseResponse<String> loginUsers(@RequestBody GetUserReq getUserReq){
        //이메일 공백 체크
        if(getUserReq.getUser_id() == null)
            return new BaseResponse<>(USERS_EMPTY_USER_ID);

        //비밀번호 공백 체크
        if(getUserReq.getUser_pwd() == null)
          return new BaseResponse<>(APP_USERS_EMPTY_USER_PASSWORD);

        try {
            userService.loginUser(getUserReq);
            String str = "로그인에 성공했습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //비밀번호 변경
    @ResponseBody
    @PatchMapping("/modifypwd")
    public BaseResponse<PatchPwdRes> modifyPwd(@RequestBody PatchPwdReq patchPwdReq) {
        //아이디 공백 체크
        if(patchPwdReq.getUser_id()== null)
            return new BaseResponse<>(USERS_EMPTY_USER_ID);

        //바꿀 비밀번호 공백 체크
        if(patchPwdReq.getUser_modify_pwd() == null)
            return new BaseResponse<>(PACTH_USERS_EMPTY_MODIFY_PASSWORD);

        try {
            PatchPwdRes patchPwdRes = userService.modifyPwd(patchPwdReq);
            return new BaseResponse<>(patchPwdRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //닉네임 변경
    @ResponseBody
    @PatchMapping("/modifynickname")
    public BaseResponse<String> modifyPwd(@RequestBody PatchNicknameReq patchNicknameReq) {
        //아이디 공백 체크
        if(patchNicknameReq.getUser_id()== null)
            return new BaseResponse<>(USERS_EMPTY_USER_ID);

        //바꿀 닉네임 공백 체크
        if(patchNicknameReq.getUser_modify_nickname() == null)
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME);

        try {
            userService.modifyNickname(patchNicknameReq);
            String str = "닉네임이 성공적으로 변경되었습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //프로필 변경
    @ResponseBody
    @PatchMapping("/modifyprofile")
    public BaseResponse<String> modifyProfile(@RequestBody PatchProfileReq patchProfileReq) {
        //아이디 공백 체크
        if(patchProfileReq.getUser_id()== null)
            return new BaseResponse<>(USERS_EMPTY_USER_ID);

        //바꿀 닉네임 공백 체크
        if(patchProfileReq.getUser_modify_profile() == null)
            return new BaseResponse<>(PATCH_USERS_EMPTY_MODIFY_PROFILE);

        try {
            userService.modifyProfile(patchProfileReq);
            String str = "프로필이 성공적으로 변경되었습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
