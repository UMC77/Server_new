package com.example.demo.src.ceo;


import com.example.demo.src.ceo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CeoDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //회원가입
    // <1>-1 사장 정보 추가
    public int createCeo(PostCeoReq postCeoReq){
        String createCeoQuery = "insert into ceo (ceo_id, ceo_pwd, ceo_phone, ceo_name) VALUES (?,?,?,?)";
        Object[] createCeoParams = new Object[]{postCeoReq.getCeo_id(),postCeoReq.getCeo_pwd(), postCeoReq.getCeo_phone(), postCeoReq.getCeo_name()};
        this.jdbcTemplate.update(createCeoQuery, createCeoParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    // <1>-2 가게 정보 추가
    public int createStore(int ceoIdx, PostStoreReq postStoreReq){
        String createStoreQuery = "insert into store (store_ceo_idx, store_num, store_name, store_phone, store_address, store_info) VALUES (?,?,?,?,?,?)";
        Object[] createStoreParams = new Object[]{ceoIdx,postStoreReq.getStore_num(), postStoreReq.getStore_name(), postStoreReq.getStore_phone(), postStoreReq.getStore_address(), postStoreReq.getStore_info()};
        this.jdbcTemplate.update(createStoreQuery, createStoreParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    // <2>
    public int checkStoreNum(String store_num){
        String checkStoreNumQuery = "select exists(select store_num from store where store_num = ?)";
        String checkStoreNumParams = store_num;
        return this.jdbcTemplate.queryForObject(checkStoreNumQuery,
                int.class,
                checkStoreNumParams);

    }

    // <3>
    public int checkCeoId(String ceo_id){
        String checkCeoIdQuery = "select exists(select ceo_id from ceo where ceo_id = ?)";
        String checkCeoIdParams = ceo_id;
        return this.jdbcTemplate.queryForObject(checkCeoIdQuery,
                int.class,
                checkCeoIdParams);

    }

    // <4>
    public int checkCeoPhone(String ceo_phone){
        String checkCeoPhoneQuery = "select exists(select ceo_phone from ceo where ceo_phone = ?)";
        String checkCeoPhoneParams = ceo_phone;
        return this.jdbcTemplate.queryForObject(checkCeoPhoneQuery,
                int.class,
                checkCeoPhoneParams);

    }

    // <5>
    public int modifyCeoPwd(PatchCeoPwdReq patchCeoPwdReq){
        String modifyPwdQuery = "update ceo set ceo_pwd = ? where ceo_id = ? ";
        Object[] modifyPwdParams = new Object[]{patchCeoPwdReq.getModify_ceo_pwd(), patchCeoPwdReq.getCeo_id()};
        this.jdbcTemplate.update(modifyPwdQuery, modifyPwdParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    // <6>
//    public Ceo loginCeo(PostLoginReq postLoginReq){
//        String loginCeoQuery = "select ceo_id,ceo_pwd from ceo where ceo_id=?";
//        String loginCeoParams = postLoginReq.getCeoId();
//        return this.jdbcTemplate.queryForObject(loginCeoQuery,
//                (rs, rowNum) -> new ceo(
//                        rs.getString("ceo_id"),
//                        rs.getString("ceo_pwd"),
//                        loginCeoParams));
//    }

    public String loginCeo(String ceo_id){
        String loginCeoQuery = "select ceo_pwd from ceo where ceo_id=?";
        String loginCeoParams = ceo_id;
        return this.jdbcTemplate.queryForObject(loginCeoQuery,String.class,loginCeoParams);
    }

}