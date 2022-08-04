package com.example.demo.src.store;


import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.GetStoreRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class StoreProvider {

    private final StoreDao storeDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreProvider(StoreDao storeDao, JwtService jwtService) {
        this.storeDao = storeDao;
        this.jwtService = jwtService;
    }

    //사장 존재여부 확인
    public int checkCeoExist(int ceo_idx) throws BaseException{
        try{
            return storeDao.checkCeoExist(ceo_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //가게 존재 여부 확인
    public int checkStoreExist(int store_idx) throws BaseException{
        try{
            return storeDao.checkStoreExist(store_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 사장의 게시물인지 확인
    public int checkCeoStoreExist(int ceo_idx,int store_idx) throws BaseException{
        try{
            return storeDao.checkUserPostExist(ceo_idx,store_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //가게 정보 조회
    public GetStoreRes viewStore(int ceo_idx) throws BaseException {
        //ceo 존재여부 확인
        if(checkCeoExist(ceo_idx) ==0){
            throw new BaseException(CEO_EMPTY_CEO_ID);
        }

        try{
            GetStoreRes getStore = storeDao.viewStore(ceo_idx);
            return getStore;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }




}
