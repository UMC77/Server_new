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
import static com.example.demo.utils.ValidationRegex.isRegexIPwd;

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
    // ceo 정보
    @ResponseBody
    @PostMapping("/signupceo")
    public BaseResponse<PostCeoRes> createCeo(@RequestBody PostCeoReq postCeoReq) {

        //사업자 등록번호 공백 check
        if(postCeoReq.getStore_num() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_STORE_NUM);

        //아이디 공백 check
        if(postCeoReq.getCeo_id() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_ID);

        //아이디 길이 check
        if(postCeoReq.getCeo_id().length()>10)
            return new BaseResponse<>(POST_CEO_INVALID_ID_LEN);

        //아이디 영문, 숫자만 허용
        if(!isRegexIPwd(postCeoReq.getCeo_id()))
            return new BaseResponse<>(POST_CEO_INVALID_ID);

        //휴대폰번호 공백 check
        if(postCeoReq.getCeo_phone() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_CEO_PHONE);

        //비밀번호 공백 check
        if(postCeoReq.getCeo_pwd() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_PWD);

        //비밀번호 길이 check
        if(postCeoReq.getCeo_pwd().length()<8)
            return new BaseResponse<>(POST_CEO_INVALID_PWD_LEN);

        //비밀번호 영문, 숫자만 허용
        if(!isRegexIPwd(postCeoReq.getCeo_pwd()))
            return new BaseResponse<>(POST_CEO_INVALID_PWD);

        //이름 공백 check
        if(postCeoReq.getCeo_name() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_CEO_NAME);

        //비밀번호 확인 공백 check
        if(postCeoReq.getCeo_pwd_chk().equals("")) {
            return new BaseResponse<>(CEO_EMPTY_PWD_CHK);
        }

        //비밀번호 확인 여부
        if(!postCeoReq.getCeo_pwd().equals(postCeoReq.getCeo_pwd_chk())) {
            return new BaseResponse<>(CEO_ERROR_CEO_PWD);
        }


        try{
            PostCeoRes postCeoRes = ceoService.createCeo(postCeoReq);
            return new BaseResponse<>(postCeoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 가게 정보
    @ResponseBody
    @PostMapping("/signupstore")
    public BaseResponse<PostStoreRes> createStore(@RequestBody PostStoreReq postStoreReq) {

        //상호 공백 check
        if(postStoreReq.getStore_name() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_STORE_NAME);

        //매장 전화번호 공백 check
        if(postStoreReq.getStore_phone() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_STORE_PHONE);

        //주소 공백 check
        if(postStoreReq.getStore_address() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_ADDRESS);

        //상세 주소 공백 check
        if(postStoreReq.getStore_address_detail() == null)
            return new BaseResponse<>(POST_CEO_EMPTY_ADDRESS_DETAIL);

        try{
            //주소와 상세 주소 결합
            String address = postStoreReq.getStore_address()+ " " + postStoreReq.getStore_address_detail();
            System.out.println(address);
            postStoreReq.setStore_address(address);


            int ceoIdxByJwt = jwtService.getCeoIdx();


            PostStoreRes postStoreRes = ceoService.createStore(ceoIdxByJwt,postStoreReq);
            return new BaseResponse<>(postStoreRes);
        } catch(BaseException exception){
//            System.out.println(exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 비밀번호 변경 */
    @ResponseBody
    @PatchMapping("/modifyceopwd")
    public BaseResponse<PatchCeoPwdRes> modifyCeoPwd(@RequestBody PatchCeoPwdReq patchCeoPwdReq){
        //아이디 공백 check
        if(patchCeoPwdReq.getCeo_id()==null) {
            return new BaseResponse<>(CEO_EMPTY_CEO_ID);
        }

        //수정 비밀번호 공백 check
        if(patchCeoPwdReq.getModify_ceo_pwd()==null) {
            return new BaseResponse<>(PATCH_CEO_EMPTY_MODIFY_PWD);
        }

        //수정 비밀번호 길이 check
        if(patchCeoPwdReq.getModify_ceo_pwd().length()<8)
            return new BaseResponse<>(PATCH_CEO_INVALID_MODIFY_PWD_LEN);

        //수정 비밀번호 영문, 숫자만 허용
        if(!isRegexIPwd(patchCeoPwdReq.getModify_ceo_pwd()))
            return new BaseResponse<>(PATCH_CEO_INVALID_MODIFY_PWD);


        try {
            PatchCeoPwdRes patchCeoPwdRes = ceoService.modifyCeoPwd(patchCeoPwdReq);
            return new BaseResponse<>(patchCeoPwdRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 로그인 */
    @ResponseBody
    @GetMapping("/login")
    public BaseResponse<String> loginCeo(@RequestBody GetCeoReq getCeoReq) {

        //아이디 공백 check
        if(getCeoReq.getCeo_id() == null)
            return new BaseResponse<>(CEO_EMPTY_CEO_ID);

        //비밀번호 공백 체크
        if(getCeoReq.getCeo_pwd()==null)
            return new BaseResponse<>(CEO_EMPTY_CEO_PWD);

        try {
            ceoService.loginCeo(getCeoReq);
            String str = "로그인에 성공했습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }




}
