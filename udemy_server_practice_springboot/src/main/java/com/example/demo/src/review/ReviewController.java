package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.ReviewProvider;
import com.example.demo.src.review.ReviewService;
import com.example.demo.src.review.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;

    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService){
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
    }

    /* 리뷰 조회 */
    @ResponseBody
    @GetMapping("/view/{storeIdx}")
    public BaseResponse<List<GetReviewRes>> getReview(@PathVariable("storeIdx") int store_idx) {
        try{

            List<GetReviewRes> getReview=reviewProvider.getReview(store_idx);

            return new BaseResponse<>(getReview);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 상태별 리뷰 조회 */
    @ResponseBody
    @GetMapping("/typeView/{storeIdx}/{type}")
    public BaseResponse<List<GetReviewTypeRes>> getReviewType(@PathVariable("storeIdx") int store_idx, @PathVariable("type") String type) {
        try{

            List<GetReviewTypeRes> getReviewType=reviewProvider.getReviewType(store_idx,type);

            return new BaseResponse<>(getReviewType);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /* 리뷰 댓글 등록 및 수정 */
    @ResponseBody
    @PatchMapping("/reply")
    public BaseResponse<String> updateReviewReply(@RequestBody PatchReviewReplyReq patchReviewReplyReq){

        //수정 댓글 공백 check
        if(patchReviewReplyReq.getReview_reply()==null) {
            return new BaseResponse<>(PATCH_REVIEW_EMPTY_MODIFY_REPLY);
        }


        try {


            reviewService.updateReviewReply(patchReviewReplyReq);
            String str = "리뷰 답변 완료하였습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            System.out.println(exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 리뷰 댓글 삭제 */
    @ResponseBody
    @PatchMapping("/deleteReply/{reviewIdx}")
    public BaseResponse<String> deleteReviewReply(@PathVariable("reviewIdx") int review_idx){
        try {

            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
            reviewService.deleteReviewReply(review_idx);

            String result = "리뷰 댓글이 삭제되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }






}
