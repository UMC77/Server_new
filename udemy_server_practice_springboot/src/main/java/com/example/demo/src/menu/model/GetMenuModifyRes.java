package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuModifyRes {
    private int menu_idx;   //메뉴 인덱스
//    private int menu_store_idx; //가게 인덱스
    private int menu_category_idx;   //메뉴 인덱스
    private String menu_img_url; //메뉴 대표 사진
    private String menu_name;   //
    private int menu_price;    //메뉴 가격
    private String menu_info;  //메뉴 설명
    private String menu_sale_chk;   //판매여부
    private String category_name;   //
    private List<GetIngredientRes> ingrdt; //재료 리스트

}
