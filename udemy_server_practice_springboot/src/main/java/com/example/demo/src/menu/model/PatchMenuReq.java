package com.example.demo.src.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PatchMenuReq {
    private String modify_menu_img_url; //수정 메뉴 사진
    private String modify_menu_name;   //수정 메뉴명
    private int modify_menu_price;    //수정 가격
    private String modify_menu_sale_chk;   //수정 판매여부
    private String modify_menu_info;  //수정 메뉴 설명
    private List<PatchIngredientReq> modify_menu_ingrdt;    //수정 재료 리스트
}
