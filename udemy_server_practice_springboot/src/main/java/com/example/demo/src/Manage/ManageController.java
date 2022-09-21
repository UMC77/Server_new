package com.example.demo.src.Manage;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.Manage.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/manage")
public class ManageController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ManageProvider manageProvider;

    @Autowired
    private final ManageService manageService;

    @Autowired
    private final JwtService jwtService;

    public ManageController(ManageProvider manageProvider, ManageService manageService, JwtService jwtService){
        this.manageProvider=manageProvider;
        this.manageService = manageService;
        this.jwtService=jwtService;
    }

    //앱 -> 서버 리뷰 입력
    @ResponseBody
    @PostMapping("/input_review")
    public BaseResponse<String> inputReview(@RequestBody ReviewReq reviewReq){
        //빵집 이름 공백 체크
        if(reviewReq.getStore_name() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_STORE_EMPTY);

        //별점 공백 체크
        if(reviewReq.getReview_score() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_SCORE_EMPTY);

        //닉네임 공백 체크
        if(reviewReq.getUser_nickname() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_NICKNAME_EMPTY);

        try{
            manageService.inputReview(reviewReq);
            String str = "리뷰를 작성하였습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException baseException){
            return new BaseResponse<>((baseException.getStatus()));
        }

    }

    //서버 -> 앱 (가게 이름을 body로 받아와야 함) 리뷰 반환
    @ResponseBody
    @GetMapping("/review")
    public BaseResponse<List<ReviewRes>> getReview(@RequestBody String store_name){
        //빵집 이름 공백 체크
        if(store_name == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_STORE_EMPTY);

        try{
            List<ReviewRes> reviewRes = manageService.getReview(store_name);
            return new BaseResponse<>(reviewRes);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //앱 -> 서버 예약
    @ResponseBody
    @PostMapping("/input_reserve")
    public BaseResponse<String> inputReserve(@RequestBody ReserveReq reserveReq){
        //빵집 이름 공백 체크
        if(reserveReq.getStore_name() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_STORE_EMPTY);

        //예약 유형 공백 체크
        if(reserveReq.getStore_reserve() == null)
            return new BaseResponse<>(BaseResponseStatus.RESERVE_RESERVE_EMPTY);

        //예약 메뉴 공백 체크
        if(reserveReq.getReserve_menu() == null)
            return new BaseResponse<>(BaseResponseStatus.RESERVE_MENU_EMPTY);

        //예약 시간 공백 체크
        if(reserveReq.getReserve_time() == null)
            return new BaseResponse<>(BaseResponseStatus.RESERVE_TIME_EMPTY);

        //닉네임 공백 체크
        if(reserveReq.getUser_nickname() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_NICKNAME_EMPTY);

        try{
            manageService.inputReserve(reserveReq);
            String str = "예약을 완료하였습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException baseException){
            return new BaseResponse<>((baseException.getStatus()));
        }

    }


    // 서버 -> 앱 예약 (가게명과 유저 닉네임 body로 입력받기)
    @ResponseBody
    @GetMapping("/reserve")
    public BaseResponse<List<ReserveRes>> getReserve(@RequestBody Reserveinfo reserveinfo){
        //빵집 이름 공백 체크
        if(reserveinfo.getStore_name() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_STORE_EMPTY);

        //유저 닉네임 공백 체크
        if(reserveinfo.getUser_nickname() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_NICKNAME_EMPTY);

        try{
            List<ReserveRes> reserveRes = manageService.getReserve(reserveinfo);
            return new BaseResponse<>(reserveRes);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //앱 -> 서버 찜
    @ResponseBody
    @PostMapping("/input_dib")
    public BaseResponse<String> inputDib(@RequestBody DibReq dibReq){
        //빵집 이름 공백 체크
        if(dibReq.getStore_name() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_STORE_EMPTY);

        //닉네임 공백 체크
        if(dibReq.getUser_nickname() == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_NICKNAME_EMPTY);

        try{
            manageService.inputDib(dibReq);
            String str = "찜을 완료하였습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException baseException){
            return new BaseResponse<>((baseException.getStatus()));
        }

    }

    //서버 -> 앱 찜 반환 (닉네임만 body로 받아도 될듯)
    @ResponseBody
    @GetMapping("/dib")
    public BaseResponse<List<DibRes>> getDib(@RequestBody String user_nickname) {
        if(user_nickname == null)
            return new BaseResponse<>(BaseResponseStatus.REVIEW_NICKNAME_EMPTY);

        try{
            List<DibRes> dibRes = manageService.getDib(user_nickname);
            return new BaseResponse<>(dibRes);
        } catch (BaseException baseException){
            return new BaseResponse<>((baseException.getStatus()));
        }
    }


}
