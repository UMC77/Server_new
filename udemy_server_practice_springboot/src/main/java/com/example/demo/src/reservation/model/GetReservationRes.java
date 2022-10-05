package com.example.demo.src.reservation.model;


import com.example.demo.src.store.model.GetStoreImgRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetReservationRes {
    private int reservation_idx;   //예약 인덱스
    private String reservation_time;   //예약 시간
    private String user_name;  //예약자명
    private int total_price;    //총 가격
    private String pickup_time;   //픽업 예정 시간
    private int total_menu_cnt;   //총 메뉴 개수
    private List<GetReservationMenuRes> reservation_menu;   //예약 메뉴명*개수
//    private String reservation_menu;   //예약 메뉴명*개수

}


