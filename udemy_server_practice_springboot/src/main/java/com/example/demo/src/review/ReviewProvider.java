package com.example.demo.src.review;


import com.example.demo.config.BaseException;
import com.example.demo.src.review.ReviewDao;
import com.example.demo.src.review.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;
import java.util.List;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ReviewProvider {

    private final ReviewDao reviewDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.jwtService = jwtService;
    }

    //리뷰 존재여부 확인
    public int checkReviewExist(int review_idx) throws BaseException{
        try{
            return reviewDao.checkReviewExist(review_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //가게 존재 여부 확인
    public int checkStoreExist(int store_idx) throws BaseException{
        try{
            return reviewDao.checkStoreExist(store_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 가게의 리뷰인지 확인
    public int checkStoreReviewExist(int store_idx,int review_idx) throws BaseException{
        try{
            return reviewDao.checkStoreReviewExist(store_idx,review_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //리뷰 리스트 조회
    public List<GetReviewRes> getReview(int store_idx,int type) throws BaseException {
        //store 존재여부 확인
        if(checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        try{
            List<GetReviewRes> getReview = reviewDao.getReview(store_idx,type);
            return getReview;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }




}
