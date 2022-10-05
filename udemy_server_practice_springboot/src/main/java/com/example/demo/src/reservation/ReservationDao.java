package com.example.demo.src.reservation;


import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.src.reservation.model.*;
import com.example.demo.src.review.model.GetReviewTypeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReservationDao {

    private JdbcTemplate jdbcTemplate;

    List<GetReservationRes> getReservationRes;
    List<GetReservationMenuRes> getReservationMenuRes;

    List<GetReservationDetailMenuRes> getReservationDetailMenuRes;


    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 예약 조회
    public List<GetReservationViewRes> getReservation(int reservation_store_idx, String view_type){
        String getReservationViewQuery="select count(case when reservation_status='wait' then 1 end) as reservation_wait_cnt,count(case when reservation_status='complete' then 1 end) as reservation_comp_cnt,count(case when reservation_status='cancel' then 1 end) as reservation_cancel_cnt ,count(*) as reservation_total_cnt from store_reservation where reservation_store_idx=?;";
        String getReservationMenuQuery="select menu_reservation_name as menu_name,menu_reservation_cnt as menu_cnt from store_reservation_menu where menu_reservation_idx=? and menu_reservation_status='active';";
        String getReservationQuery="";
        Object[] getReservationParams;
        if(view_type.equals("all")) {
            getReservationQuery = "select r.reservation_idx,r.reservation_time,r.reservation_user_name as user_name,sum(m.menu_reservation_price) as total_price,r.reservation_pickup_time as pickup_time,count(m.menu_reservation_price) as total_menu_cnt from store_reservation as r join store_reservation_menu as m on m.menu_reservation_idx=r.reservation_idx where r.reservation_store_idx=? and m.menu_reservation_status='active' group by reservation_idx;";
            getReservationParams = new Object[]{reservation_store_idx };
        }
        else {
            getReservationQuery="select r.reservation_idx,r.reservation_time,r.reservation_user_name as user_name,sum(m.menu_reservation_price) as total_price,r.reservation_pickup_time as pickup_time,count(m.menu_reservation_price) as total_menu_cnt from store_reservation as r join store_reservation_menu as m on m.menu_reservation_idx=r.reservation_idx where r.reservation_store_idx=? and m.menu_reservation_status='active' and r.reservation_status=? group by reservation_idx;";
            getReservationParams = new Object[]{reservation_store_idx,view_type };

        }

        String finalGetReservationQuery = getReservationQuery;
        return this.jdbcTemplate.query(getReservationViewQuery,
                (rs, rowNum) -> new GetReservationViewRes(
                        rs.getInt("reservation_wait_cnt"),
                        rs.getInt("reservation_comp_cnt"),
                        rs.getInt("reservation_cancel_cnt"),
                        rs.getInt("reservation_total_cnt"),
                        getReservationRes = this.jdbcTemplate.query(finalGetReservationQuery,
                                (rk, rowNum1) -> new GetReservationRes(
                                        rk.getInt("reservation_idx"),
                                        rk.getString("reservation_time"),
                                        rk.getString("user_name"),
                                        rk.getInt("total_price"),
                                        rk.getString("pickup_time"),
                                        rk.getInt("total_menu_cnt"),
                                        getReservationMenuRes = this.jdbcTemplate.query(getReservationMenuQuery,
                                                (rt, rowNum2) -> new GetReservationMenuRes(
                                                        rt.getString("menu_name"),
                                                        rt.getInt("menu_cnt")

                                                )
                                        ,rk.getInt("reservation_idx"))

                                )
                        ,getReservationParams)
                )

                ,reservation_store_idx);
    }

    //예약 상세 조회
    public GetReservationDetailRes getReservationDetail(int reservation_idx){
        String getReservationDetailQuery="select r.reservation_idx,r.reservation_user_name as user_name,r.reservation_time,sum(m.menu_reservation_price) as total_price,count(m.menu_reservation_price) as total_menu from store_reservation as r join store_reservation_menu as m on m.menu_reservation_idx=r.reservation_idx where r.reservation_idx=? group by reservation_idx;";
        String getReservationDetailMenuQuery="select menu_reservation_name as menu_name,menu_reservation_cnt as menu_cnt,menu_reservation_price as menu_price from store_reservation_menu where menu_reservation_idx=? and menu_reservation_status='active';";

        return this.jdbcTemplate.queryForObject(getReservationDetailQuery,
                (rs, rowNum) -> new GetReservationDetailRes(
                        rs.getInt("reservation_idx"),
                        rs.getString("user_name"),
                        rs.getString("reservation_time"),
                        rs.getInt("total_price"),
                        rs.getInt("total_menu"),
                        getReservationDetailMenuRes = this.jdbcTemplate.query(getReservationDetailMenuQuery,
                                (rk, rowNum1) -> new GetReservationDetailMenuRes(
                                        rk.getString("menu_name"),
                                        rk.getInt("menu_cnt"),
                                        rk.getInt("menu_price")
                                )
                                ,reservation_idx)
                )

                ,reservation_idx);
    }


    public int checkStoreExist(int store_idx){
        String checkStoreExistQuery = "select exists(select store_idx from store where store_idx = ?)";
        int checkStoreExistParams = store_idx;
        return this.jdbcTemplate.queryForObject(checkStoreExistQuery,
                int.class,
                checkStoreExistParams);
    }


    // <4>
    public int checkReservationExist(int reservation_idx){
        String checkReservationExistQuery = "select exists(select reservation_idx from store_reservation where reservation_idx = ?)";
        int checkReservationExistParams = reservation_idx;
        return this.jdbcTemplate.queryForObject(checkReservationExistQuery,
                int.class,
                checkReservationExistParams);

    }






}