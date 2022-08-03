package com.example.demo.src.ceo;


import com.example.demo.config.BaseException;
import com.example.demo.src.ceo.model.GetCeoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class CeoProvider {

    private final CeoDao ceoDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CeoProvider(CeoDao ceoDao, JwtService jwtService) {
        this.ceoDao = ceoDao;
        this.jwtService = jwtService;
    }

    //사업자등록번호 존재 여부 check
    public int checkStoreNum(String store_num) throws BaseException{
        try{
            return ceoDao.checkStoreNum(store_num);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //아이디 존재 여부 check
    public int checkCeoId(String ceo_id) throws BaseException{
        try{
            return ceoDao.checkCeoId(ceo_id);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //휴대폰번호 존재 여부 check
    public int checkCeoPhone(String ceo_phone) throws BaseException{
        try{
            return ceoDao.checkCeoPhone(ceo_phone);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
