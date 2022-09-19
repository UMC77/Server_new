package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

@RestController
@RequestMapping("/store")
public class StoreController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final StoreProvider storeProvider;
    @Autowired
    private final StoreService storeService;
    @Autowired
    private final JwtService jwtService;

    public StoreController(StoreProvider storeProvider, StoreService storeService, JwtService jwtService){
        this.storeProvider = storeProvider;
        this.storeService = storeService;
        this.jwtService = jwtService;
    }

    /* 가게 정보 조회 */
    @ResponseBody
    @GetMapping("/view")
    public BaseResponse<GetStoreRes> getStore() {
        try{
            //jwt에서 idx 추출.
            int ceoIdxByJwt = jwtService.getCeoIdx();
            GetStoreRes getStore=storeProvider.viewStore(ceoIdxByJwt);

            return new BaseResponse<>(getStore);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }



    /* 가게 정보(전체 정보) 수정 */
    @ResponseBody
    @PatchMapping("/{storeIdx}")
    public BaseResponse<String> modifyStore(@PathVariable("storeIdx") int store_idx, @RequestBody PatchStoreReq patchStoreReq){

        //수정 가게 대표 사진 공백 check
        if(patchStoreReq.getModify_store_img_url().size()<1){
            return new BaseResponse<>(PATCH_STORE_EMPTY_MODIFY_IMGRUL);
        }

        //수정 상호명 공백 check
        if(patchStoreReq.getModify_store_name()==null) {
            return new BaseResponse<>(PATCH_STORE_EMPTY_MODIFY_STORE_NAME);
        }

        //사업자등록번호 공백 check
        if(patchStoreReq.getModify_store_num()==null) {
            return new BaseResponse<>(PATCH_STORE_EMPTY_MODIFY_STORE_NUM);
        }

        //매장 주소 공백 check
        if(patchStoreReq.getModify_store_address()==null) {
            return new BaseResponse<>(PATCH_STORE_EMPTY_MODIFY_STORE_ADDRESS);
        }

        //매장 전화번호 공백 check
        if(patchStoreReq.getModify_store_phone()==null) {
            return new BaseResponse<>(PATCH_STORE_EMPTY_MODIFY_STORE_PHONE);
        }

        //운영일 공백 check
        if(patchStoreReq.getModify_store_time()==null) {
            return new BaseResponse<>(PATCH_STORE_EMPTY_MODIFY_STORE_TIME);
        }

        //휴무일 공백 check
        if(patchStoreReq.getModify_store_holiday()==null) {
            return new BaseResponse<>(PATCH_STORE_EMPTY_MODIFY_STORE_HOLI);
        }


        try {

            int ceoIdxByJwt = jwtService.getCeoIdx();

            storeService.modifyStore(ceoIdxByJwt,store_idx,patchStoreReq);
            String str = "가게정보가 성공적으로 변경되었습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }




}
