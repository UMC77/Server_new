package com.example.demo.src.Manage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReserveRes {
    private String store_name; //빵집 이름
    private String store_reserve; //예약 유형
    private String reserve_menu; //예약 메뉴
    private String menu_price; //빵 가격
    private String user_nickname; //닉네임
    private String reserve_time; //예약 시간
}
