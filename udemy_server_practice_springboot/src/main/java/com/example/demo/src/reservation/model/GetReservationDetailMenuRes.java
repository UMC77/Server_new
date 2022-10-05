package com.example.demo.src.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReservationDetailMenuRes {
    private String menu_name;   //예약 메뉴명
    private int menu_cnt;    //예약 메뉴별 수량
    private int menu_price;    //예약 메뉴 가격

}


