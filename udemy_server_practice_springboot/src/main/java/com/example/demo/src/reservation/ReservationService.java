//package com.example.demo.src.reservation;
//
//
//import com.example.demo.config.BaseException;
//import com.example.demo.src.reservation.ReservationDao;
//import com.example.demo.src.reservation.ReservationProvider;
//import com.example.demo.src.reservation.model.*;
//import com.example.demo.utils.JwtService;
//import com.example.demo.utils.SHA256;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import static com.example.demo.config.BaseResponseStatus.*;
//
//// Service Create, Update, Delete 의 로직 처리
//@Service
//public class ReservationService {
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private final ReservationDao reservationDao;
//    private final ReservationProvider reservationProvider;
//    private final JwtService jwtService;
//
//
//    @Autowired
//    public ReservationService(ReservationDao reservationDao, ReservationProvider reservationProvider, JwtService jwtService) {
//        this.reservationDao = reservationDao;
//        this.reservationProvider = reservationProvider;
//        this.jwtService = jwtService;
//
//    }
//
//
//
//
//}
