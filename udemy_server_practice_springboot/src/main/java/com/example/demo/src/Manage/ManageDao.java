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
        String inputReviewQuery = "INSERT INTO store_review(review_store_name, review_score, review_comment, review_date, review_user_nickname, review_menu, review_user_profile) VALUES(?,?,?,?,?,?,?)";
        Object[] inputReviewParams = new Object[]{reviewReq.getStore_name(), reviewReq.getReview_score(), reviewReq.getReview_comment(), reviewReq.getReview_date(), reviewReq.getUser_nickname(), reviewReq.getReview_menu(), reviewReq.getUser_profile()};
        this.jdbcTemplate.update(inputReviewQuery, inputReviewParams);

    }

    //리뷰 반환
    public List<ReviewRes> getReview(String store_name){
        String getReviewQuery = "select review_store_name, review_score, review_comment, review_date, review_user_nickname, review_user_profile, review_menu FROM store_review WHERE review_store_name = ?";
        String getReviewParam = store_name;

        return this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new ReviewRes(
                        rs.getString("store_name"),
                        rs.getFloat("review_score"),
                        rs.getString("review_comment"),
                        rs.getTimestamp("review_date"),
                        rs.getString("user_nickname"),
                        rs.getString("user_profile"),
                        rs.getString("review_menu")
                ),getReviewParam);
    }

    //예약 입력
    public void inputReserve(ReserveReq reserveReq){

    }

    //예약 반환
    public ReserveRes getReserve(String store_name, String user_nickname){

    }


    //찜 입력
    public void inputDib(DibReq dibReq){

    }

    //찜 반환
    public DibRes getDip(String user_nickname) {

    }

}
