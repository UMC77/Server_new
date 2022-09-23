package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    private int total_review_cnt;   //전체 리뷰 개수
    private int reply_review_cnt;   //답변한 리뷰 개수
    private int wait_review_cnt;    //미답변 리뷰 개수
    private List<GetReviewTypeRes> review;  //리뷰 목록
}


