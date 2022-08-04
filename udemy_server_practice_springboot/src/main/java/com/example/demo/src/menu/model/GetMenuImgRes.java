package com.example.demo.src.store.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreImgRes {
    private int picture_idx; //사진 인덱스
    private String picture_url; //가게 대표 사진

}
