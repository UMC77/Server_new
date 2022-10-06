package com.example.demo.src.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchRes {
    private String picture_url; //빵집 메인 사진
    private String store_name; //빵집 이름
    private String store_address; //빵집 주소
    private Float review_score; //빵집 별점
    private Integer review_cnt; //리뷰 개수
    private String store_reserve; //예약 유형
}
