package com.example.demo.src.ceo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCeoReq {
    private String ceo_id;
    private String ceo_pwd;

}
