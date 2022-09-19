package com.example.demo.src.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetReservationViewRes {
    private String reservation_total_cnt;   //유형별 reservation 개수
    private List<GetReservationRes> reservationList;    //reservation 리스트
}


