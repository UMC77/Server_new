package com.example.demo.src.menu.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchCategoryReq {
    private int category_idx;   //카테고리 인덱스
//    private int category_store_idx; //카테고리 가게 인덱스
    private String modify_category_name;   //수정 카테고리명
}
