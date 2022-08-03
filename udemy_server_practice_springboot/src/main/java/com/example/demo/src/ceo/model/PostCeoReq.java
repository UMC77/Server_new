package com.example.demo.src.ceo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCeoReq {
    private String ceo_id;  //사장님 아이디
    private int store_num;  //사업자등록번호
    private String ceo_pwd; //비번
    private String ceo_name;    //사장님 이름
    private String ceo_phone;   //사장님 번호
//    private String store_name;  //상호
//    private String store_phone;    //매장 번호
//    private String store_address;  //주소
//    private String store_info; //가게 위치 상세
}
