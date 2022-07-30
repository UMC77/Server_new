package com.example.demo.src.ceo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCeoReq {
    private String ceoId;  //사장님 아이디
    private int storeNum;  //사업자등록번호
    private String ceoPwd; //비번
    private String ceoName;    //이름
    private String ceoPhone;   //번호
    private String storeName;  //상호
    private String storePhone;    //매장 번호
    private String storeAddress;  //주소
    private String storeInfo; //가게 정보
}
