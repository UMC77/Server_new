package com.example.demo.src.ceo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostStoreRes {
    private String jwt;
    private int ceo_idx;
}
