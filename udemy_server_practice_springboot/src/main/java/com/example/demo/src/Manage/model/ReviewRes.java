package com.example.demo.src.Manage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ReviewRes {
    private String store_name; //빵집 이름
    private Float review_score; //리뷰 별점
    private String review_comment; //리뷰 내용
    private Date review_date; //리뷰 작성 날짜
    private String user_nickname; //닉네임
    private String user_profile; //프로필 사진
    private String review_menu; //예약 메뉴
}
