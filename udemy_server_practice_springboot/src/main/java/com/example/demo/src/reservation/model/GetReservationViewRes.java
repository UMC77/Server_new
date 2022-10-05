package com.example.demo.src.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetReservationViewRes {
    private int reservation_wait_cnt;   //접수 대기 reservation 개수
    private int reservation_comp_cnt;   //접수 완료 reservation 개수
    private int reservation_cancel_cnt;   //취소 주문 reservation 개수
    private int reservation_total_cnt;   //전체 reservation 개수
    private List<GetReservationRes> reservationList;    //reservation 리스트
}


