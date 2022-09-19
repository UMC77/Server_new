package com.example.demo.src.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSearchReq {
//    private int store_idx;   //가게 인덱스
    private String category_name;   //카테고리 이름
    private String keyword; //검색 키워드

}
