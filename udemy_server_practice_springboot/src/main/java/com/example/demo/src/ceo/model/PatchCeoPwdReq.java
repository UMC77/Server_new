package com.example.demo.src.ceo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchCeoPwdReq {
    private int ceoId;    //사장님 아이디
    private String modifyCeoPwd;   //수정 비밀번호
}
