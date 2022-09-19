package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCategoryRes {
    private int category_idx;   //카테고리 인덱스
    private String category_name; //카테고리 이름


}
