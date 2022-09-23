package com.example.demo.src.review;

import com.example.demo.config.BaseException;

import com.example.demo.src.review.ReviewDao;
import com.example.demo.src.review.ReviewProvider;
import com.example.demo.src.review.model.PatchReviewReplyReq;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;


    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService;

    }

    /* 리뷰 댓글 업로드 */
    public void updateReviewReply(PatchReviewReplyReq patchReviewReplyReq) throws BaseException {


        try{
            int result = reviewDao.updateReviewReply(patchReviewReplyReq);


            if(result == 0){
                throw new BaseException(MODIFY_FAIL_REVIEW_REPLY);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /* 리뷰 댓글 삭제 */
    public void deleteReviewReply(int review_idx) throws BaseException {

        //리뷰 존재 유무 check
        if(reviewProvider.checkReviewExist(review_idx) ==0){
            throw new BaseException(REVIEW_EMPTY_REVIEW_IDX);
        }


        try{
            int result = reviewDao.deleteReviewReply(review_idx);
            if(result == 0){
                throw new BaseException(DELETE_FAIL_REVIEW_REPLY);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }




}
