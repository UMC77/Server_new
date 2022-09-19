package com.example.demo.src.store;


import com.example.demo.config.BaseException;

import com.example.demo.src.store.model.PatchStoreReq;
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
            int res_idx = storeDao.modifyStore(store_idx,patchStoreReq);

            storeDao.deleteStoreImgs(res_idx);

            for(int i=0; i< patchStoreReq.getModify_store_img_url().size(); i++) {
                storeDao.modifyStoreImgs(res_idx, patchStoreReq.getModify_store_img_url().get(i));
            }
            if(res_idx == 0){
                throw new BaseException(MODIFY_FAIL_STORE);
            }
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
