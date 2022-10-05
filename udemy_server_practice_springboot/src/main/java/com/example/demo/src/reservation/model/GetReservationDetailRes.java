package com.example.demo.src.reservation.model;


import com.example.demo.src.store.model.GetStoreImgRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetReservationDetailRes {
    private int reservation_idx;   //예약 인덱스
    private String user_name;  //예약자명
    private String reservation_time;   //예약 시간
    private int total_price;    //가격 합계
    private int total_menu;    //메뉴 수량 합계
    private List<GetReservationDetailMenuRes> reservation_menu;   //예약 메뉴, 수량, 금액 리스트

}


