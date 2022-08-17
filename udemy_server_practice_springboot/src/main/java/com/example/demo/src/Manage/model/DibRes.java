package com.example.demo.src.Manage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DibRes {
    private String picture_url; //빵집 프로필 사진
    private String store_name; //빵집 이름
    private Float store_score; //빵집 별점
    private String store_reserve; //예약 유형
    private String user_nickname; //닉네임

}
