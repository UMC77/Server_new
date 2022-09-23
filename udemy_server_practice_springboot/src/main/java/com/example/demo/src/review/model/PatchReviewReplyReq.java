package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchReviewReplyReq {
    private int review_idx; //리뷰 인덱스
    private String review_reply;    //리뷰 댓글
}
