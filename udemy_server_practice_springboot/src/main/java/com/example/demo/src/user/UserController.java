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

    @RequestMapping
    @PostMapping("/signup")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        //이메일 공백 체크
        if(postUserReq.getUser_id() == null)
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);

        //이메일 중복 체크
        if(!isRegexEmail(postUserReq.getUser_id()))
                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);

        //비밀번호 공백 체크
        if(postUserReq.getUser_pwd() == null)
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);

        //닉네임 공백 체크
        if(postUserReq.getUser_nickname() == null)
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME);

        //전화번호 공백 체크
        if(postUserReq.getUser_phone() == null)
            return new BaseResponse<>(POST_USERS_EMPTY_PHONENUM);

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
          return new BaseResponse<>(USERS_EMPTY_USER_PASSWORD);

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
    @GetMapping("/modifypwd")
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
    @GetMapping("/modifynickname")
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
    @GetMapping("/modifyprofile")
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

    /*

    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/users
    public BaseResponse<GetUserRes> getUsers(@RequestParam(required = true) String Email) {
        try{
            // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
            if(Email.length()==0){
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            }
            // 이메일 정규표현
            if(!isRegexEmail(Email)){
                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
            }
            GetUserRes getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{userIdx}") // (GET) 127.0.0.1:9000/users/:userIdx
    public BaseResponse<GetUserRes> getUserByIdx(@PathVariable("userIdx")int userIdx) {
        try{

            GetUserRes getUsersRes = userProvider.getUsersByIdx(userIdx);
            return new BaseResponse<>(getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @PostMapping("") // (POST) 127.0.0.1:9000/users
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        if(postUserReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        // 이메일 정규표현
        if(!isRegexEmail(postUserReq.getEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/{userIdx}") // (PATCH) 127.0.0.1:9000/users/:userIdx
    public BaseResponse<String> modifyUserName(@PathVariable("userIdx") int userIdx, @RequestBody User user){
        try {
             TODO: jwt는 다음주차에서 배울 내용입니다!
            jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }


            PatchUserReq patchUserReq = new PatchUserReq(userIdx,user.getNickName());
            userService.modifyUserName(patchUserReq);

            String result = "";
        return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    */
}
