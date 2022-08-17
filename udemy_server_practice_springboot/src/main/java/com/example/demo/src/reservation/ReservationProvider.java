package com.example.demo.src.reservation;


import com.example.demo.config.BaseException;
import com.example.demo.src.reservation.ReservationDao;
import com.example.demo.src.reservation.model.GetReservationDetailRes;
import com.example.demo.src.reservation.model.GetReservationRes;
import com.example.demo.src.store.model.GetStoreRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;
import java.util.List;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ReservationProvider {

    private final ReservationDao reservationDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReservationProvider(ReservationDao reservationDao, JwtService jwtService) {
        this.reservationDao = reservationDao;
        this.jwtService = jwtService;
    }

    //예약 존재 여부 확인
    public int checkReservationExist(int reservation_idx) throws BaseException{
        try{
            return reservationDao.checkReservationExist(reservation_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //가게 존재 여부 확인
    public int checkStoreExist(int store_idx) throws BaseException{
        try{
            return reservationDao.checkStoreExist(store_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 가게의 예약인지 확인
    public int checkStoreReservationExist(int store_idx,int reservation_idx) throws BaseException{
        try{
            return reservationDao.checkStoreReservationExist(store_idx,reservation_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //예약 리스트 조회
    public List<GetReservationRes> getReservation(int store_idx,int type) throws BaseException {
        //store 존재여부 확인
        if(checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        try{
            List<GetReservationRes> getReservation = reservationDao.getReservation(store_idx,type);
            return getReservation;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    //상세 메뉴 조회
    public GetReservationDetailRes getReservationDetail(int store_idx, int reservation_idx) throws BaseException {
        //store 존재여부 확인
        if(checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        //reservation 존재 여부 확인
        if(checkStoreExist(reservation_idx) ==0){
            throw new BaseException(RESERVATION_EMPTY_RESERVATION_IDX);
        }

        //가게의 예약인지 확인
        if(checkStoreReservationExist(store_idx,reservation_idx) ==0){
            throw new BaseException(RESERVATION_EMPTY_STORE_RESERVATION);
        }

        try{
            GetReservationDetailRes getReservationDetail = reservationDao.getReservationDetail(store_idx,reservation_idx);
            return getReservationDetail;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }



}
