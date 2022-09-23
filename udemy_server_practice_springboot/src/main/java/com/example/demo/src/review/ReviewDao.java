package com.example.demo.src.review;


import com.example.demo.src.menu.model.GetCategoryMenuRes;
import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.src.review.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    List<GetReviewTypeRes> getReviewTypeRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 리뷰 조회
    public List<GetReviewRes> getReview(int store_idx){
        String getReviewCntQuery="select count(*) as total_review_cnt,count(case when review_reply_status='complete' then 1 end) as reply_review_cnt, count(case when review_reply_status='wait' then 1 end) as wait_review_cnt from store_review where review_store_idx=?;";

        String getReviewQuery="select review_idx,replace(review_user_nickname, substr(review_user_nickname, char_length(review_user_nickname)-1, 1), '*') user_id, review_date as review_time, review_score, review_menu, review_order_num, review_img_url, review_comment\n" +
                "from store_review where review_store_idx=?;";

        return this.jdbcTemplate.query(getReviewCntQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt("total_review_cnt"),
                        rs.getInt("reply_review_cnt"),
                        rs.getInt("wait_review_cnt"),
                        getReviewTypeRes = this.jdbcTemplate.query(getReviewQuery,
                                (rk, rownum) -> new GetReviewTypeRes(
                                        rk.getInt("review_idx"),
                                        rk.getString("user_id"),
                                        rk.getString("review_time"),
                                        rk.getFloat("review_score"),
                                        rk.getString("review_menu"),
                                        rk.getInt("review_order_num"),
                                        rk.getString("review_img_url"),
                                        rk.getString("review_comment")
                                )
                        ,store_idx)
                )
                ,store_idx);
    }

    // 상태별 리뷰 조회
    public List<GetReviewTypeRes> getReviewType(int store_idx, String type){
        String getReviewTypeQuery="";
        if(type.equals("all")) {
            getReviewTypeQuery = "select review_idx,replace(review_user_nickname, substr(review_user_nickname, char_length(review_user_nickname)-1, 1), '*') user_id, review_date as review_time, review_score, review_menu, review_order_num, review_img_url, review_comment\n" +
                    "from store_review where review_store_idx=?;";
        }
        else {
            getReviewTypeQuery= "select review_idx,replace(review_user_nickname, substr(review_user_nickname, char_length(review_user_nickname)-1, 1), '*') user_id, review_date as review_time, review_score, review_menu, review_order_num, review_img_url, review_comment from store_review where review_store_idx=? and review_reply_status=?;";
        }

        Object[] getReviewTypeParams = new Object[]{store_idx,type };


        return this.jdbcTemplate.query(getReviewTypeQuery,
                (rs, rowNum) -> new GetReviewTypeRes(
                        rs.getInt("review_idx"),
                        rs.getString("user_id"),
                        rs.getString("review_time"),
                        rs.getFloat("review_score"),
                        rs.getString("review_menu"),
                        rs.getInt("review_order_num"),
                        rs.getString("review_img_url"),
                        rs.getString("review_comment")
                )
        ,getReviewTypeParams);
    }

    // 리뷰 답변 업로드
    public int updateReviewReply(PatchReviewReplyReq patchReviewReplyReq){
        String updateReviewReplyQuery = "update store_review set review_reply = ?, review_reply_status = 'complete' where review_idx = ? ";
        Object[] updateReviewReplyParams = new Object[]{patchReviewReplyReq.getReview_reply(), patchReviewReplyReq.getReview_idx()};

        return this.jdbcTemplate.update(updateReviewReplyQuery,updateReviewReplyParams);
    }

    // <2>
    public int checkStoreExist(int store_idx){
        String checkStoreExistQuery = "select exists(select store_idx from store where store_idx = ?)";
        int checkStoreExistParams = store_idx;
        return this.jdbcTemplate.queryForObject(checkStoreExistQuery,
                int.class,
                checkStoreExistParams);
    }

    // <3>
    public int checkReviewExist(int review_idx){
        String checkReviewExistQuery = "select exists(select review_idx from store_review where review_idx = ?)";
        int checkReviewExistParams = review_idx;
        return this.jdbcTemplate.queryForObject(checkReviewExistQuery,
                int.class,
                checkReviewExistParams);
    }


    // 리뷰 답변 삭제
    public int deleteReviewReply(int review_idx){
        String deleteReviewReplyQuery = "update store_review set review_reply_status = 'wait', review_reply = NULL where review_idx = ? ";

        return this.jdbcTemplate.update(deleteReviewReplyQuery,review_idx);
    }

}