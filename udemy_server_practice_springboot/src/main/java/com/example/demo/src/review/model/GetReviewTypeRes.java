package com.example.demo.src.review.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewTypeRes {
    private int review_idx;   //리뷰 인덱스
    private String user_id; //작성자 아이디
    private String review_time;   //리뷰 작성 날짜
    private float review_score;  //별점
    private String review_menu; //리뷰 메뉴명
    private int order_num;    //주문번호
    private String review_img_url;   //리뷰 이미지 url
    private String review_comment;   //리뷰 내용
}


