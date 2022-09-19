package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetCategoryMenuRes {
    private String category_name;   //메뉴명
    private List<GetMenuRes> menu;    //재료 리스트

}
