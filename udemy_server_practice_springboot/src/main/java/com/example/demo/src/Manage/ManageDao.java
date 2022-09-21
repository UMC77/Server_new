package com.example.demo.src.Manage;

import com.example.demo.src.Manage.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ManageDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }


    //리뷰 입력
    public void inputReview(ReviewReq reviewReq){
        String inputReviewQuery = "INSERT INTO store_review2(store_name, review_score, review_comment, review_date, user_nickname, review_menu, user_profile) VALUES(?,?,?,?,?,?,?)";
        Object[] inputReviewParams = new Object[]{reviewReq.getStore_name(), reviewReq.getReview_score(), reviewReq.getReview_comment(), reviewReq.getReview_date(), reviewReq.getUser_nickname(), reviewReq.getReview_menu(), reviewReq.getUser_profile()};
        this.jdbcTemplate.update(inputReviewQuery, inputReviewParams);

    }

    //리뷰 반환
    public List<ReviewRes> getReview(String store_name){
        String getReviewQuery = "select store_name, review_score, review_comment, review_date, user_nickname, user_profile, review_menu FROM store_review2 WHERE store_name = ?";
        String getReviewParam = store_name;

        return this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new ReviewRes(
                        rs.getString("store_name"),
                        rs.getFloat("review_score"),
                        rs.getString("review_comment"),
                        rs.getDate("review_date"),
                        rs.getString("user_nickname"),
                        rs.getString("user_profile"),
                        rs.getString("review_menu")
                ),getReviewParam);
    }

    //예약 입력
    public void inputReserve(ReserveReq reserveReq){
        String inputReserveQuery = "INSERT INTO store_reserve(reserve_store_name, reserve_type, reserve_menu, reserve_price, reserve_user_nick_name, reserve_time) value (?,?,?,?,?,?)";
        Object[] inputReserveParams = new Object[]{reserveReq.getStore_name(), reserveReq.getStore_reserve(), reserveReq.getReserve_menu(),reserveReq.getMenu_price(), reserveReq.getUser_nickname(), reserveReq.getReserve_time()};
        this.jdbcTemplate.update(inputReserveQuery, inputReserveParams);

    }

    //예약 반환
    public List<ReserveRes> getReserve(String store_name1, String user_nickname){
        String getReserveQuery = "select reserve_store_name, reserve_type, reserve_menu, reserve_price, reserve_user_nick_name, reserve_time FROM store_reserve WHERE reserve_store_name = ?";
        String getReserveParam = store_name1;
        return this.jdbcTemplate.query(getReserveQuery,
                (rs, rowNum) -> new ReserveRes(
                        rs.getString("store_name"),
                        rs.getString("store_reserve"),
                        rs.getString("reserve_menu"),
                        rs.getString("menu_price"),
                        rs.getString("user_nickname"),
                        rs.getString("reserve_time")
                ), getReserveParam);

    }


    //찜 입력
    public void inputDib(DibReq dibReq){
        String inputDibQuery = " INSERT INTO user_dibs(dibs_store_name, dibs_user_nickname) value (?,?)";
        Object[] inputDibParams = new Object[]{dibReq.getStore_name(), dibReq.getUser_nickname()};
        this.jdbcTemplate.update(inputDibQuery,inputDibParams);

    }

    //찜 반환
    public List<DibRes> getDib(String user_nickname) {
        String getDibQuery = "select dibs_store_name FROM user_dibs WHERE dibs_user_nickname = ?";
        String getDibParam = user_nickname;
        return this.jdbcTemplate.query(getDibQuery,
                (rs, rowNum) -> new DibRes(
                        rs.getString("store_name")
                ),getDibParam);
    }

}
