package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuCntRes {
    private int total_menu_cnt;   //전체 메뉴 개수
    private int sale_menu_cnt;   //판매중 메뉴 개수
    private int soldout_menu_cnt;   //품절 메뉴 개수
    private int hide_menu_cnt;   //숨김 메뉴 개수


}
