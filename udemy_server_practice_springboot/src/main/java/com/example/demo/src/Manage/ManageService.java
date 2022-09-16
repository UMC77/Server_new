package com.example.demo.src.Manage;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.Manage.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ManageService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManageDao manageDao;
    private final ManageProvider manageProvider;
    private final JwtService jwtService;

    @Autowired
    public ManageService(ManageDao manageDao, ManageProvider manageProvider, JwtService jwtService){
        this.manageDao = manageDao;
        this.manageProvider = manageProvider;
        this.jwtService = jwtService;
    }

    //리뷰 입력
    public void inputReview(ReviewReq reviewReq) throws BaseException{
        try{
            manageDao.inputReview(reviewReq);
        } catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    //리뷰 반환
    public List<ReviewRes> getReview(String store_name) throws BaseException{
        try{
            List<ReviewRes> reviewRes = manageDao.getReview(store_name);
            return reviewRes;
        } catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    //예약 입력
    public void inputReserve(ReserveReq reserveReq) throws BaseException{
        try {
            manageDao.inputReserve(reviewReq);
        } catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    //예약 반환
    public ReserveRes getReserve(String store_name, String user_nickname) throws BaseException{
        try{
            ReserveRes reserveRes = manageDao.getReserve(store_name, user_nickname);
            return reserveRes;
        } catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    //찜 입력
    public void inputDib(DibReq dibReq) throws BaseException{
        try{
            manageDao.inputDib(dibReq);
        } catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    //찜 반환
    public DibRes getDib(String user_nickname) throws BaseException{
        try{
            DibRes dibRes = manageDao.getDib(user_nickname);
            return dibRes;
        } catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR.DATABASE_ERROR);
        }
    }

}
