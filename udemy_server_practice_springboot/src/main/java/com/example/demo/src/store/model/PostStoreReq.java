package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostStoreReq {
    private String ceo_idx;
    private String List<PostStoreImgUrlReq> store_img_url;
    private String store_time;   //운영시간
    private String store_holiday;    //매장 휴무일
    private String store_info;
}
