package com.example.demo.src.store;


import com.example.demo.config.BaseException;

import com.example.demo.src.ceo.model.PatchStoreReq;
import com.example.demo.src.ceo.model.PostStoreReq;
import com.example.demo.src.ceo.model.PostStoreRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class StoreService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StoreDao storeDao;
    private final StoreProvider storeProvider;
    private final JwtService jwtService;


    @Autowired
    public StoreService(StoreDao storeDao, StoreProvider storeProvider, JwtService jwtService) {
        this.storeDao = storeDao;
        this.storeProvider = storeProvider;
        this.jwtService = jwtService;

    }

//    /* 가게 정보 추가 */
//    public PostStoreRes addStore(int ceo_idx, PostStoreReq postStoreReq) throws BaseException {
//
//        try{
//            int store_idx = storeDao.addStore(ceo_idx, postStoreReq);
//            for(int i=0; i< postStoreReq.getStore_img_url().size(); i++) {
//                ceoDao.insertStoreImgs(store_idx, postStoreReq.getStore_img_url().get(i));  //**dao에만 있는 메소드
//            }
//            return new PostStoreRes(store_idx);
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

    /* 가게 정보 수정 */
    public void modifyStore(int ceo_idx,int store_idx, PatchStoreReq patchStoreReq) throws BaseException {

        //사장 존재 유무 check
        if(storeProvider.checkCeoExist(ceo_idx) ==0){
            throw new BaseException(CEO_EMPTY_CEO_ID);
        }

        //가게 존재 유무 check
        if(storeProvider.checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        //***사장의 가게인지 check
        if(storeProvider.checkCeoStoreExist(ceo_idx, store_idx)==0){
            throw new BaseException(STORE_EMPTY_CEO_STORE);
        }

        try{
            int result = storeDao.modifyStore(store_idx,patchStoreReq);
            for(int i=0; i< patchStoreReq.getModify_store_img_url().size(); i++) {
                ceoDao.modifyStoreImgs(store_idx, patchStoreReq.getModify_store_img_url().get(i));  //**dao에만 있는 메소드
            }
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_STORE);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
