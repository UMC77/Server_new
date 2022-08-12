package com.example.demo.src.ceo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostStoreReq {
    private String store_name;  //상호
    private String store_phone;    //매장 번호
    private String store_address;  //주소
    private String store_address_detail;  //상세 주소
    private String store_info; //가게 위치 상세
}
