package com.example.demo.src.store.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.utils.List;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreRes {
    private int store_idx;   //가게 인덱스
    private int ceo_idx; //사장 인덱스
    private String picture_url; //가게 대표 사진
    private List<GetStoreImgRes> store_img_url;
    private String store_name;   //상호명
    private String store_num;    //사업자등록번호
    private String store_address;    //매장주소
    private String store_phone;  //매장 전화번호
    private String store_time;   //매장 운영시간
    private String store_holiday;    //매장 휴무일
    private String store_info;   //매장 정보

}
