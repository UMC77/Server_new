package com.example.demo.src.reservation.model;


import com.example.demo.src.store.model.GetStoreImgRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetReservationMenuRes {
//    private int menu_reservation_idx;   //예약 메뉴 인덱스
    private String menu_name;   //예약 메뉴명
//    private String menu_str;  //예약 메뉴명 * 수량
    private int menu_cnt;    //예약 메뉴별 수량

}


