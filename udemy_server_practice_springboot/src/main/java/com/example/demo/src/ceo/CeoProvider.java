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


//    public GetCeoRes getCeoByEmail(String email) throws BaseException{
//        try{
//            GetCeoRes getCeoRes = ceoDao.getCeoByEmail(email);
//            return getCeoRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//                    }
//
//
//    public GetUserRes getUsersByIdx(int userIdx) throws BaseException{
//        try{
//            GetUserRes getUsersRes = userDao.getUsersByIdx(userIdx);
//            return getUsersRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


//    public int checkEmail(String email) throws BaseException{
//        try{
//            return userDao.checkEmail(email);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


    //사업자등록번호 존재 여부 check
    public int checkStoreNum(String storeNum) throws BaseException{
        try{
            return ceoDao.checkStoreNum(storeNum);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //아이디 존재 여부 check
    public int checkCeoId(String ceoId) throws BaseException{
        try{
            return ceoDao.checkCeoId(ceoId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //휴대폰번호 존재 여부 check
    public int checkCeoPhone(String ceoPhone) throws BaseException{
        try{
            return ceoDao.checkCeoPhone(ceoPhone);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
