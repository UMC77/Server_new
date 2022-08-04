package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchStoreReq {
//    private String ceo_idx;
    private String List<PatchStoreImgUrlReq> modify_store_img_url;
    private String modify_store_name;   //상호명
    private String modify_store_num;    //사업자등록번호
    private String modify_store_address;    //매장주소
    private String modify_store_phone;  //매장 전화번호
    private String modify_store_time;   //매장 운영시간
    private String modify_store_holiday;    //매장 휴무일
    private String modify_store_info;   //매장 소개
}
