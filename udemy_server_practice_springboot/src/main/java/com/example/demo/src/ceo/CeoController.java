package com.example.demo.src.ceo;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.ceo.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
//import static com.example.demo.config.BaseResponseStatus.POST_USERS_INVALID_EMAIL;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/ceo")
public class CeoController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CeoProvider ceoProvider;
    @Autowired
    private final CeoService ceoService;
    @Autowired
    private final JwtService jwtService;




    public CeoController(CeoProvider ceoProvider, CeoService ceoService, JwtService jwtService){
        this.ceoProvider = ceoProvider;
        this.ceoService = ceoService;
        this.jwtService = jwtService;
    }

    /* 회원가입 */
    // Body
    @ResponseBody
    @PostMapping("/signup") // (POST) 127.0.0.1:9000/ceo
    public BaseResponse<PostCeoRes> createCeo(@RequestBody PostCeoReq postCeoReq) {

        //사업자 등록번호 공백 check
        if(postCeoReq.getStoreNum() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_STORE_NUM);

        //아이디 공백 check
        if(postCeoReq.getCeoId() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_ID);

        //아이디 길이 check
        if(postCeoReq.getCeoId().length()>10)
            return new BaseResponse<>(POST_CEO_INVALID_ID_LEN);

        //아이디 영문, 숫자만 허용
        if(!isRegexIPwd(postCeoReq.getCeoId()))
            return new BaseResponse<>(POST_CEO_INVALID_ID);

        //휴대폰번호 공백 check
        if(postCeoReq.getCeoPhone() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_PHONE);

        //비밀번호 공백 check
        if(postCeoReq.getPwd() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_PWD);

        //비밀번호 길이 check
        if(postCeoReq.getCeoId().length()<8)
            return new BaseResponse<>(POST_CEO_INVALID_PWD_LEN);

        //비밀번호 영문, 숫자만 허용
        if(!isRegexIPwd(postCeoReq.getCeoPwd()))
            return new BaseResponse<>(POST_CEO_INVALID_PWD);

        //이름 공백 check
        if(postCeoReq.getCeoName() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_CEO_NAME);

        //상호 공백 check
        if(postCeoReq.getStoreName() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_STORE_NAME);

        //매장 전화번호 공백 check
        if(postCeoReq.getStorePhone() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_STORE_PHONE);

        //주소 공백 check
        if(postCeoReq.getAddress() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_ADDRESS);

        try{
            PostCeoRes postCeoRes = ceoService.createCeo(postCeoReq);
            return new BaseResponse<>(postCeoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 비밀번호 변경 */
    @ResponseBody
    @PatchMapping("/modifyceopwd")
    public BaseResponse<PatchCeoPwdRes> modifyCeoPwd(@RequestBody PatchCeoPwdReq patchCeoPwdReq){
        //아이디 공백 check
        if(patchCeoPwdReq.getCeoId()==null) {
            return new BaseResponse<>(CEO_EMPTY_CEO_ID);
        }

        //수정 비밀번호 공백 check
        if(patchCeoPwdReq.getModifyCeoPwd()==null) {
            return new BaseResponse<>(PATCH_CEO_EMPTY_MODIFY_PWD);
        }

        //수정 비밀번호 길이 check
        if(patchCeoPwdReq.getCeoId().length()<8)
            return new BaseResponse<>(POST_CEO_INVALID_PWD_LEN);

        //수정 비밀번호 영문, 숫자만 허용
        if(!isRegexIPwd(patchCeoPwdReq.getCeoPwd()))
            return new BaseResponse<>(POST_CEO_INVALID_PWD);

        try {
            PatchCeoPwdRes patchCeoPwdRes = ceoService.modifyCeoPwd(patchCeoPwdReq);
            return new BaseResponse<>(PatchCeoPwdRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 로그인 */
    @ResponseBody
    @GetMapping("/login")
    public BaseResponse<String> loginCeo(@RequestBody GetCeoReq getCeoReq) {

        //아이디 공백 check
        if(getCeoReq.getCeoId() == null){
            return new BaseResponse<>(CEO_EMPTY_CEO_ID);
        }

        //비밀번호 공백 체크
        if(getCeoReq.getCeoPwd()==null) {
            return new BaseResponse<>(CEO_EMPTY_CEO_PWD);
        }

        try {
            ceoService.loginCeo(getCeoReq);
            String str = "로그인에 성공했습니다.";
            return new BaseResponse<>((exception.getStatus()));
        }

    }


    /**
     * 회원 조회 API
     * [GET] /ceo
     * 검색 조회 API
     * [GET] /ceo? keyword=
     * @return BaseResponse<GetCeoRes>
     */
    //Query String
//    @ResponseBody
//    @GetMapping("") // (GET) 127.0.0.1:9000/ceo
//    public BaseResponse<GetCeoRes> getCeo(@RequestParam(required = true) String Email) {
//        try{
//            // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
//            if(Email.length()==0){
//                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
//            }
//            // 이메일 정규표현
//            if(!isRegexEmail(Email)){
//                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
//            }
//            GetCeoRes getCeoRes = ceoProvider.getUsersByEmail(Email);
//            return new BaseResponse<>(getCeoRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    @ResponseBody
//    @GetMapping("/{ceoIdx}") // (GET) 127.0.0.1:9000/ceo/:ceoIdx
//    public BaseResponse<GetCeoRes> getCeoByIdx(@PathVariable("userIdx")int userIdx) {
//        try{
//
//            GetUserRes getUsersRes = userProvider.getUsersByIdx(userIdx);
//            return new BaseResponse<>(getUsersRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }



}
