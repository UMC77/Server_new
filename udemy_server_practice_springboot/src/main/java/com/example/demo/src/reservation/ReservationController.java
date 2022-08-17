package com.example.demo.src.reservation;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.reservation.ReservationProvider;
import com.example.demo.src.reservation.ReservationService;
import com.example.demo.src.reservation.model.GetReservationDetailRes;
import com.example.demo.src.reservation.model.GetReservationRes;
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
    @GetMapping("/view/{type}")
    public BaseResponse<List<GetReservationRes>> getReservation(@PathVariable("type") int type) {
        try{
            //jwt에서 idx 추출.
            int storeIdxByJwt = jwtService.getStoreIdx();
            List<GetReservationRes> getReservation=reservationProvider.getReservation(storeIdxByJwt,type);

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
            //jwt에서 idx 추출.
            int storeIdxByJwt = jwtService.getStoreIdx();
            GetReservationDetailRes getReservationDetail=reservationProvider.getReservationDetail(storeIdxByJwt,reservation_idx);

            return new BaseResponse<>(getReservationDetail);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

//    /* 접수 대기 조회 */
//    @ResponseBody
//    @GetMapping("/viewwait")
//    public BaseResponse<List<GetReservationRes>> getWaitReservation() {
//        try{
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//            List<GetReservationRes> getWaitReservation=reservationProvider.getWaitReservation(storeIdxByJwt);
//
//            return new BaseResponse<>(getWaitReservation);
//        } catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /* 접수 완료 조회 */
//    @ResponseBody
//    @GetMapping("/viewcomplete")
//    public BaseResponse<List<GetReservationRes>> getCompleteReservation() {
//        try{
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//            List<GetReservationRes> getCompleteReservation=reservationProvider.getCompleteReservation(storeIdxByJwt);
//
//            return new BaseResponse<>(getCompleteReservation);
//        } catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /* 거절 주문 조회 */
//    @ResponseBody
//    @GetMapping("/viewdeny")
//    public BaseResponse<List<GetReservationRes>> getDenyReservation() {
//        try{
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//            List<GetReservationRes> getDenyReservation=reservationProvider.getDenyReservation(storeIdxByJwt);
//
//            return new BaseResponse<>(getDenyReservation);
//        } catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }




}
