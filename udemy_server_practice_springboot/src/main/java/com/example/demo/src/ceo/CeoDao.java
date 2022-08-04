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

    // <1>-1
    public int createCeo(PostCeoReq postCeoReq){
        String createCeoQuery = "insert into ceo (ceo_id, ceo_pwd, ceo_name, ceo_phone) VALUES (?,?,?,?)";
        Object[] createCeoParams = new Object[]{postCeoReq.getCeo_id(),postCeoReq.getCeo_pwd(), postCeoReq.getCeo_name(), postCeoReq.getCeo_phone()};
        this.jdbcTemplate.update(createCeoQuery, createCeoParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    // <1>-2
    public int createStore(PostCeoReq postCeoReq){
        String createCeoQuery = "insert into store (store_num, store_name, store_phone, store_address, store_info) VALUES (?,?,?,?,?)";
        Object[] createCeoParams = new Object[]{postCeoReq.getStore_num(), postCeoReq.getStore_name(), postCeoReq.getStore_phone(), postCeoReq.getStore_address(), postCeoReq.getStore_info()};
        this.jdbcTemplate.update(createCeoQuery, createCeoParams);

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
    public int modifyPwd(PatchCeoPwdReq patchCeoPwdReq){
        String modifyPwdQuery = "update ceo set ceo_pwd = ? where ceo_id = ? ";
        Object[] modifyPwdParams = new Object[]{patchCeoPwdReq.getCeo_pwd(), patchCeoPwdReq.getCeo_id()};
        this.jdbcTemplate.update(modifyPwdQuery, modifyPwdParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    // <6>
    public Ceo loginCeo(PostLoginReq postLoginReq){
        String loginCeoQuery = "select ceo_id,ceo_pwd from ceo where ceo_id=?";
        String loginCeoParams = postLoginReq.getCeoId();
        return this.jdbcTemplate.queryForObject(loginCeoQuery,
                (rs, rowNum) -> new ceo(
                        rs.getInt("ceo_id"),
                        rs.getString("ceo_pwd"),
                        loginCeoParams);
    }

}