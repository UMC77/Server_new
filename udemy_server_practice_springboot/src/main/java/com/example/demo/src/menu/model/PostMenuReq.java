package com.example.demo.src.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostMenuReq {
    private String menu_img_url;    //메뉴 사진
    private String menu_name;   //메뉴명
    private int menu_price;    //가격
    private String menu_sale_chk;   //판매여부
    private String menu_info;  //메뉴 설명
    private List<PostIngredientReq> menu_ingrdt;    //재료 리스트
}
