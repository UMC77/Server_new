//package com.example.demo.src.review;
//
//
//import com.example.demo.src.menu.model.GetCategoryMenuRes;
//import com.example.demo.src.menu.model.GetMenuRes;
//import com.example.demo.src.review.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Repository
//public class ReviewDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource){
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    // 리뷰 조회
//    public List<GetReviewRes> getReview(int menu_store_idx, String type){
//        String getCategoryMenuQuery="";
//        if(type.equals("all")) getCategoryMenuQuery = "select distinct sc.category_name as category_name, sc.category_idx as category_idx from store_menu as sm join store_category as sc on sc.category_idx=sm.menu_category_idx where menu_store_idx=? and menu_status='ACTIVE'";
//        else if(type.equals("sale")) getCategoryMenuQuery = "select distinct sc.category_name as category_name, sc.category_idx as category_idx from store_menu as sm join store_category as sc on sc.category_idx=sm.menu_category_idx where menu_store_idx=? and menu_sale_chk='sale' and menu_status='ACTIVE'";
//        else if(type.equals("soldOut")) getCategoryMenuQuery = "select distinct sc.category_name as category_name, sc.category_idx as category_idx from store_menu as sm join store_category as sc on sc.category_idx=sm.menu_category_idx where menu_store_idx=? and menu_sale_chk='soldOut' and menu_status='ACTIVE'";
//        else if(type.equals("hide")) getCategoryMenuQuery = "select distinct sc.category_name as category_name, sc.category_idx as category_idx from store_menu as sm join store_category as sc on sc.category_idx=sm.menu_category_idx where menu_store_idx=? and menu_sale_chk='hide' and menu_status='ACTIVE'";
//
//        String getMenuQuery="select menu_idx, menu_img_url, menu_name, menu_price, menu_sale_chk from store_menu where menu_category_idx=? and menu_status='ACTIVE'";
//
//        int getCategoryMenuParams = menu_store_idx;
//
//        return this.jdbcTemplate.query(getCategoryMenuQuery,
//                (rs, rowNum) -> new GetCategoryMenuRes(
//                        rs.getString("category_name"),
//                        getMenuRes = this.jdbcTemplate.query(getMenuQuery,
//                                (rk, rownum) -> new GetMenuRes(
//                                        rk.getInt("menu_idx"),
//                                        rk.getString("menu_img_url"),
//                                        rk.getString("menu_name"),
//                                        rk.getInt("menu_price"),
//                                        rk.getString("menu_sale_chk")
//                                )
//                                ,rs.getInt("category_idx"))
//                )
//                ,getCategoryMenuParams);
//    }
//
////    // <1>
////    public int updateReviewReply(PatchReviewReplyReq patchReviewReplyReq){
////        String updateReviewReplyQuery = "update store_review set review_reply = ?, review_reply_status = 'complete' where review_idx = ? ";
////        Object[] updateReviewReplyParams = new Object[]{patchReviewReplyReq.getReview_reply(), patchReviewReplyReq.getReview_reply_status(), patchReviewReplyReq.getReview_idx()};
////
////        return this.jdbcTemplate.update(updateReviewReplyQuery,updateReviewReplyParams);
////    }
//
//    // <2>
//    public int checkStoreExist(int store_idx){
//        String checkStoreExistQuery = "select exists(select store_idx from store where store_idx = ?)";
//        int checkStoreExistParams = store_idx;
//        return this.jdbcTemplate.queryForObject(checkStoreExistQuery,
//                int.class,
//                checkStoreExistParams);
//    }
//
//    // <3>
//    public int checkReviewExist(int review_idx){
//        String checkReviewExistQuery = "select exists(select review_idx from store_review where review_idx = ?)";
//        int checkReviewExistParams = review_idx;
//        return this.jdbcTemplate.queryForObject(checkReviewExistQuery,
//                int.class,
//                checkReviewExistParams);
//    }
//
//    // <4>
//    public int checkStoreReviewExist(int review_idx){
//        String checkStoreReviewExistQuery = "select exists(select review_store_idx from store_review where review_idx = ?)";
//        int checkStoreReviewExistParams = review_idx;
//        return this.jdbcTemplate.queryForObject(checkStoreReviewExistQuery,
//                int.class,
//                checkStoreReviewExistParams);
//
//    }
//
////    // <5>
////    public int deleteReviewReply(DeleteReviewReplyReq deleteReviewReplyReq){
////        String deleteReviewReplyQuery = "update store_review set review_reply_status = 'wait', review_reply = NULL where review_idx = ? ";
////        Object[] deleteReviewReplyParams = new Object[]{deleteReviewReplyReq.getReview_reply_status(), deleteReviewReplyReq.getReview_reply(), deleteReviewReplyReq.getReview_idx()};
////
////        return this.jdbcTemplate.update(deleteReviewReplyQuery,deleteReviewReplyParams);
////    }
//
//}