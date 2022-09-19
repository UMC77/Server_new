package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuRes {
    private int menu_idx;   //메뉴 인덱스
    private String menu_img_url; //메뉴 대표 사진
    private String menu_name;   //메뉴명
    private int menu_price;    //메뉴 가격
    private String menu_sale_chk;   //판매여부

}
