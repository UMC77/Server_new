//package com.example.demo.src.reservation;
//
//
//import com.example.demo.src.menu.model.GetMenuRes;
//import com.example.demo.src.reservation.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Repository
//public class ReservationDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource){
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    // 예약 조회
//    public List<GetReservationViewRes> getReservation(int reservation_store_idx, String view_type){
//        String getReservationQuery="";
//        if(view_type.equals("all")) getReservationQuery = "select menu_idx, menu_store_idx, menu_category_idx, menu_img_url, menu_name, menu_price, menu_sale_chk from store_menu where menu_store_idx=? and menu_status='ACTIVE'";
//        else if(view_type.equals("wait")) getReservationQuery = "select menu_idx, menu_store_idx, menu_category_idx, menu_img_url, menu_name, menu_price, menu_sale_chk from store_menu where menu_store_idx=? and menu_sale_chk='wait' and menu_status='ACTIVE'";
//        else if(view_type.equals("complete")) getReservationQuery = "select menu_idx, menu_store_idx, menu_category_idx, menu_img_url, menu_name, menu_price, menu_sale_chk from store_menu where menu_store_idx=? and menu_sale_chk='complete' and menu_status='ACTIVE'";
//        else if(view_type.equals("deny")) getReservationQuery = "select menu_idx, menu_store_idx, menu_category_idx, menu_img_url, menu_name, menu_price, menu_sale_chk from store_menu where menu_store_idx=? and menu_sale_chk='deny' and menu_status='ACTIVE'";
//
//
//        int getReservationParams = reservation_store_idx;
//        return this.jdbcTemplate.query(getReservationQuery,
//                (rs, rowNum) -> new GetReservationRes(
//                        rs.getInt("reservation_idx"),
//                        reservation_store_idx,
//                        rs.getString("reservation_time"),
//                        rs.getString("user_name"),
//                        rs.getInt("total_price"),
//                        rs.getString("pickup_time"),
//                        rs.getInt("total_menu_cnt"),
//                        rs.getString("reservation_menu")
//                )
//
//                ,getReservationParams);
//    }
//
//    // <1>
//    public int modifyStore(PatchStoreReq patchStoreReq){
//        String modifyStoreQuery = "update store set store_img_url = ?, store_name = ?, store_num = ?, store_address = ?, store_phone = ?, store_time = ?, store_holiday = ?, store_info = ? where store_idx = ? ";
//        Object[] modifyStoreParams = new Object[]{patchStoreReq.getStore_img_url(), patchStoreReq.getStore_name(), patchStoreReq.getStore_num(), patchStoreReq.getStore_address(), patchStoreReq.getStore_phone(), patchStoreReq.getStore_time(), patchStoreReq.getStore_holiday(), patchStoreReq.getStore_info(), patchStoreReq.getStore_idx()};
//
//        return this.jdbcTemplate.update(modifyStoreQuery,modifyStoreParams);
//    }
//
//    // <2>
//    public int modifyStoreImgs(PatchStoreImgsReq patchStoreImgsReq){
//        String modifyStoreImgsQuery = "update store_picture set picture_url = ? where picture_store_idx = ? ";
//        Object[] modifyStoreImgsParams = new Object[]{patchStoreImgsReq.getPicture_url(), patchStoreImgsReq.getPicture_store_idx()};
//
//        return this.jdbcTemplate.update(modifyStoreImgsQuery,modifyStoreImgsParams);
//    }
//
//    // <3>
//    public int checkCeoExist(int ceo_idx){
//        String checkCeoExistQuery = "select exists(select ceo_idx from ceo where ceo_idx = ?)";
//        String checkCeoExistParams = ceo_idx;
//        return this.jdbcTemplate.queryForObject(checkCeoExistQuery,
//                int.class,
//                checkCeoExistParams);
//    }
//
//    // <4>
//    public int checkReservationExist(int reservation_idx){
//        String checkReservationExistQuery = "select exists(select reservation_idx from reservation where reservation_idx = ?)";
//        String checkReservationExistParams = reservation_idx;
//        return this.jdbcTemplate.queryForObject(checkReservationExistQuery,
//                int.class,
//                checkReservationExistParams);
//
//    }
//
//    // <5>
//    public int checkStoreReservationExist(int reservation_idx){
//        String checkStoreReservationExistQuery = "select exists(select reservation_store_idx from reservation where reservation_idx = ?)";
//        String checkStoreReservationExistParams = reservation_idx;
//        return this.jdbcTemplate.queryForObject(checkStoreReservationExistQuery,
//                int.class,
//                checkStoreReservationExistParams);
//
//    }
//
//    // <6>
//    public int checkStoreExist(int store_idx){
//        String checkStoreExistQuery = "select exists(select store_idx from store where store_idx = ?)";
//        String checkStoreExistParams = store_idx;
//        return this.jdbcTemplate.queryForObject(checkStoreExistQuery,
//                int.class,
//                checkStoreExistParams);
//
//    }
//
//}