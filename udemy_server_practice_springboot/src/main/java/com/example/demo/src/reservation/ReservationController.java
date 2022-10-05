package com.example.demo.src.reservation;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.reservation.ReservationProvider;
import com.example.demo.src.reservation.ReservationService;
import com.example.demo.src.reservation.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexIPwd;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReservationProvider reservationProvider;
    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private final JwtService jwtService;


    public ReservationController(ReservationProvider reservationProvider, ReservationService reservationService, JwtService jwtService){
        this.reservationProvider = reservationProvider;
        this.reservationService = reservationService;
        this.jwtService = jwtService;
    }

    /* 예약 조회 */
    @ResponseBody
    @GetMapping("/{storeIdx}/{viewType}")
    public BaseResponse<List<GetReservationViewRes>> getReservation(@PathVariable("storeIdx") int store_idx,@PathVariable("viewType") String view_type) {
        try{
            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
            List<GetReservationViewRes> getReservation=reservationProvider.getReservation(store_idx,view_type);

            return new BaseResponse<>(getReservation);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 예약 상세 조회 */
    @ResponseBody
    @GetMapping("/view/{reservationIdx}")
    public BaseResponse<GetReservationDetailRes> getReservationDetail(@PathVariable("reservationIdx") int reservation_idx) {
        try{

            GetReservationDetailRes getReservationDetail=reservationProvider.getReservationDetail(reservation_idx);

            return new BaseResponse<>(getReservationDetail);
        } catch (BaseException exception){
//            System.out.println(exception);
            return new BaseResponse<>(exception.getStatus());
        }
    }




}
