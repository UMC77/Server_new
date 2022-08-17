package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewReplyRes {
    private int review_idx;   //리뷰 인덱스
    private int review_store_idx; //가게 인덱스
    private String review_reply;   //댓글(답변) 내용
}


