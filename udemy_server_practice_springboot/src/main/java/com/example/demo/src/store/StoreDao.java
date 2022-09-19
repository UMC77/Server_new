package com.example.demo.src.store;


import com.example.demo.src.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StoreDao {

    private JdbcTemplate jdbcTemplate;
    private List<GetStoreImgRes> getStoreImgRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int modifyStore(int store_idx, PatchStoreReq patchStoreReq){
        String modifyStoreQuery = "update store as s inner join ceo as c on s.store_ceo_idx=c.ceo_idx set s.store_name = ?, c.store_num = ?, s.store_address = ?, s.store_phone = ?, s.store_time = ?, s.store_holiday = ?, s.store_info = ? where s.store_idx = ? ";
        Object[] modifyStoreParams = new Object[]{patchStoreReq.getModify_store_name(), patchStoreReq.getModify_store_num(), patchStoreReq.getModify_store_address(), patchStoreReq.getModify_store_phone(), patchStoreReq.getModify_store_time(), patchStoreReq.getModify_store_holiday(), patchStoreReq.getModify_store_info(),store_idx};

        return this.jdbcTemplate.update(modifyStoreQuery,modifyStoreParams);
    }

    public int deleteStoreImgs(int store_idx){
        String deleteStoreImgsQuery = "update store_picture set picture_status = 'INACTIVE' where picture_store_idx = ? ";
        return this.jdbcTemplate.update(deleteStoreImgsQuery,store_idx);
    }


    // <2>
    public int modifyStoreImgs(int store_idx,PatchStoreImgUrlReq patchStoreImgsReq){
//        String modifyStoreImgsQuery = "update store_picture set picture_url = ? where picture_store_idx = ? ";
        String modifyStoreImgsQuery = "insert into store_picture(picture_store_idx,picture_url) values(?,?)";
        Object[] modifyStoreImgsParams = new Object[]{store_idx,patchStoreImgsReq.getModify_picture_url()};

        return this.jdbcTemplate.update(modifyStoreImgsQuery,modifyStoreImgsParams);
    }

    // <3>
    public int checkCeoExist(int ceo_idx){
        String checkCeoExistQuery = "select exists(select ceo_idx from ceo where ceo_idx = ?)";
        int checkCeoExistParams = ceo_idx;
        return this.jdbcTemplate.queryForObject(checkCeoExistQuery,
                int.class,
                checkCeoExistParams);

    }

    // <4>
    public int checkStoreExist(int store_idx){
        String checkStoreExistQuery = "select exists(select store_idx from store where store_idx = ?)";
        int checkStoreExistParams = store_idx;
        return this.jdbcTemplate.queryForObject(checkStoreExistQuery,
                int.class,
                checkStoreExistParams);

    }

    // <5>
    public int checkCeoStoreExist(int ceo_idx,int store_idx){
        String checkCeoStoreExistQuery = "select exists(select store_idx from store where store_idx = ? and store_ceo_idx=?)";
        Object[] checkCeoStoreExistParams = new Object[]{store_idx,ceo_idx};
        return this.jdbcTemplate.queryForObject(checkCeoStoreExistQuery,
                int.class,
                checkCeoStoreExistParams);

    }

    // <6>-1
    public GetStoreRes viewStore(int ceo_idx){
        String getStoresByIdxQuery = "select store_idx, store_ceo_idx, store_name, store_phone, store_address, store_info,store_time, store_holiday from store where store_ceo_idx=?";
//        int getStoresByIdxParams = ceo_idx;
        return this.jdbcTemplate.queryForObject(getStoresByIdxQuery,
                (rs, rowNum) -> new GetStoreRes(
                        rs.getInt("store_idx"),
                        rs.getInt("store_ceo_idx"),
                        rs.getString("store_name"),
                        rs.getString("store_phone"),
                        rs.getString("store_address"),
                        rs.getString("store_info"),
                        rs.getString("store_time"),
                        rs.getString("store_holiday"),
                        getStoreImgRes = this.jdbcTemplate.query("select pi.picture_idx, pi.picture_url from store_picture as pi join store as s where s.store_idx=pi.picture_store_idx and pi.picture_status='ACTIVE';",
                                (rk, rownum) -> new GetStoreImgRes(
                                        rk.getInt("picture_idx"),
                                        rk.getString("picture_url")
                                )
                        )
                )
        ,ceo_idx);


    }

    // <6>-2
//    public GetStoreImgRes getStoreImgsByIdx(int picture_store_idx){
//        String getStoreImgsByIdxQuery = "select picture_store_idx, picture_idx, picture_url from store_picture where picture_store_idx=?";
//        int getStoreImgsByIdxParams = picture_store_idx;
//        return this.jdbcTemplate.queryForObject(getStoreImgsByIdxQuery,
//                (rs, rowNum) -> new GetStoreImgRes(
//                        rs.getInt("picture_store_idx"),
//                        rs.getInt("picture_idx"),
//                        rs.getString("picture_url")),
//                getStoreImgsByIdxParams);
//    }
}