package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.utils.List;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuRes {
    private int menu_idx;   //메뉴 인덱스
    private int menu_store_idx; //가게 인덱스
    private String picture_url; //가게 대표 사진
    private String store_name;   //상호명
    private String store_num;    //사업자등록번호
    private String store_address;    //매장주소
    private String store_phone;  //매장 전화번호
    private String store_time;   //매장 운영시간
    private String store_holiday;    //매장 휴무일
    private String store_info;   //매장 정보

}
