package com.example.demo.src.menu;


import com.example.demo.src.menu.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class MenuDao {

    private JdbcTemplate jdbcTemplate;
    private List<GetIngredientRes> getIngrdtRes;
    private List<GetMenuRes> getMenuRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 메뉴 조회
    public List<GetCategoryMenuRes> getCategoryMenu(int menu_store_idx,String view_type){
        String getCategoryMenuQuery="select distinct sc.category_name as category_name, sc.category_idx as category_idx from store_menu as sm join store_category as sc on sc.category_idx=sm.menu_category_idx where menu_store_idx=? and menu_status='ACTIVE';";
        String getMenuQuery="select menu_idx, menu_img_url, menu_name, menu_price, menu_sale_chk from store_menu where menu_category_idx=? and menu_status='ACTIVE' and menu_sale_chk=?";

//        Object[] getCategoryMenuParams = new Object[]{menu_store_idx, menu_store_idx,menu_store_idx,menu_store_idx};

        return this.jdbcTemplate.query(getCategoryMenuQuery,
                (rs, rowNum) -> new GetCategoryMenuRes(
                        rs.getString("category_name"),
                        getMenuRes = this.jdbcTemplate.query(getMenuQuery,
                                (rk, rownum) -> new GetMenuRes(
                                        rk.getInt("menu_idx"),
                                        rk.getString("menu_img_url"),
                                        rk.getString("menu_name"),
                                        rk.getInt("menu_price"),
                                        rk.getString("menu_sale_chk")
                                )
                        ,new Object[]{rs.getInt("category_idx"),view_type})
                )
        ,menu_store_idx);
    }

    // 카테고리 조회
    public List<GetCategoryRes> getCategory(int store_idx){
        String getCategoryQuery="select category_idx, category_name from store_category where category_store_idx=?";

        return this.jdbcTemplate.query(getCategoryQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("category_idx"),
                        rs.getString("category_name")
                )
        ,store_idx);

    }

    // 메뉴 검색 조회
    public List<GetMenuRes> getSearchMenu(GetSearchReq getSearchReq){
        String getSearchMenuQuery="";
        Object[] getSearchMenuParams = new Object[]{getSearchReq.getCategory_name(),'%'+getSearchReq.getKeyword()+'%'};

        if(getSearchReq.getCategory_name().equals("전체")) {
            getSearchMenuQuery = "select sm.menu_idx as menu_idx, sm.menu_img_url as menu_img_url, sm.menu_name as menu_name, sm.menu_price as menu_price, sm.menu_sale_chk as menu_sale_chk from store_menu as sm join store_category as sc on sc.category_idx=sm.menu_category_idx where sm.menu_status='ACTIVE' and sm.menu_name like ?;";

            return this.jdbcTemplate.query(getSearchMenuQuery,
                    (rs, rowNum) -> new GetMenuRes(
                            rs.getInt("menu_idx"),
                            rs.getString("menu_img_url"),
                            rs.getString("menu_name"),
                            rs.getInt("menu_price"),
                            rs.getString("menu_sale_chk")
                            )
                    ,'%'+getSearchReq.getKeyword()+'%');
        }
        else {
            getSearchMenuQuery = "select sm.menu_idx as menu_idx, sm.menu_img_url as menu_img_url, sm.menu_name as menu_name, sm.menu_price as menu_price, sm.menu_sale_chk as menu_sale_chk from store_menu as sm join store_category as sc on sc.category_idx=sm.menu_category_idx where sm.menu_status='ACTIVE' and sc.category_name=? and sm.menu_name like ?;";

            return this.jdbcTemplate.query(getSearchMenuQuery,
                    (rs, rowNum) -> new GetMenuRes(
                            rs.getInt("menu_idx"),
                            rs.getString("menu_img_url"),
                            rs.getString("menu_name"),
                            rs.getInt("menu_price"),
                            rs.getString("menu_sale_chk")
                            )
                    ,getSearchMenuParams);
        }


    }

    //메뉴 수정창 조회
    public GetMenuModifyRes getModifyMenu(int menu_idx){
        String getModiMenuQuery = "select m.menu_idx as menu_idx, m.menu_category_idx as menu_category_idx, m.menu_img_url as menu_img_url, m.menu_name as menu_name, m.menu_price as menu_price, m.menu_sale_chk as menu_sale_chk, m.menu_info as menu_info, c.category_name as category_name from store_menu as m join store_category as c on c.category_idx=m.menu_category_idx  where m.menu_idx=? and m.menu_status='ACTIVE'";
//        int getModiMenuParams = menu_store_idx;
        return this.jdbcTemplate.queryForObject(getModiMenuQuery,
                (rs, rowNum) -> new GetMenuModifyRes(
                        menu_idx,
                        rs.getInt("menu_category_idx"),
                        rs.getString("menu_img_url"),
                        rs.getString("menu_name"),
                        rs.getInt("menu_price"),
                        rs.getString("menu_info"),
                        rs.getString("menu_sale_chk"),
                        rs.getString("category_name"),
                        getIngrdtRes = this.jdbcTemplate.query("select ingrdt_idx, ingrdt_name from store_menu_ingredient where ingrdt_menu_idx=? and ingrdt_status='ACTIVE';",
                                (rk, rownum) -> new GetIngredientRes(
                                        rk.getInt("ingrdt_idx"),
                                        rk.getString("ingrdt_name")
                                )
                        ,menu_idx)
                )
        ,menu_idx);
    }

    //메뉴 개수 조회
    public GetMenuCntRes getMenuCnt(int store_idx){
        String getMenuCntQuery = "select count(*) as total_menu_cnt,count(case when menu_sale_chk='sale' then 1 end) as sale_menu_cnt, count(case when menu_sale_chk='sold out' then 1 end) as soldout_menu_cnt,count(case when menu_sale_chk='hide' then 1 end) as hide_menu_cnt from store_menu where menu_status='ACTIVE' and menu_store_idx=?;";
//        int getModiMenuParams = menu_store_idx;
        return this.jdbcTemplate.queryForObject(getMenuCntQuery,
                (rs, rowNum) -> new GetMenuCntRes(
                        rs.getInt("total_menu_cnt"),
                        rs.getInt("sale_menu_cnt"),
                        rs.getInt("soldout_menu_cnt"),
                        rs.getInt("hide_menu_cnt")
                )
        ,store_idx);
    }


    // 메뉴 등록
    public int createMenu(int category_idx, PostMenuReq postMenuReq){
        String createMenuQuery = "insert into store_menu (menu_store_idx,menu_category_idx,menu_img_url,menu_name,menu_price,menu_info,menu_sale_chk)  values((select category_store_idx from store_category where category_idx=?),?,?,?,?,?,?);";
        Object[] createMenuParams = new Object[]{category_idx,category_idx ,postMenuReq.getMenu_img_url(), postMenuReq.getMenu_name(),postMenuReq.getMenu_price(),postMenuReq.getMenu_info(), postMenuReq.getMenu_sale_chk()};
        this.jdbcTemplate.update(createMenuQuery, createMenuParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    // 카테고리 생성
    public int addCategory(int store_idx){
        String createCategoryQuery = "insert into store_category (category_store_idx) VALUES (?)";
        int createCategoryParams = store_idx;
        this.jdbcTemplate.update(createCategoryQuery, createCategoryParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }


    public int insertIngrdt(int menu_idx, PostIngredientReq postIngredientReq){
        String insertIngrdtQuery = "insert into store_menu_ingredient (ingrdt_menu_idx, ingrdt_name) VALUES (?,?)";
        Object[] insertIngrdtParams = new Object[]{menu_idx, postIngredientReq.getIngrdt_name()};
        this.jdbcTemplate.update(insertIngrdtQuery, insertIngrdtParams);

        String lastInsertIdQuery = "select last_insert_id()";
//        return this.jdbcTemplate.update(insertIngrdtQuery, insertIngrdtParams);
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }



    // 메뉴 수정
    public int modifyMenu(int menu_idx,PatchMenuReq patchMenuReq){
        String modifyMenuQuery = "update store_menu set menu_img_url = ?, menu_name = ?, menu_price = ?, menu_sale_chk = ?, menu_info = ? where menu_idx = ? ";
        Object[] modifyMenuParams = new Object[]{patchMenuReq.getModify_menu_img_url(), patchMenuReq.getModify_menu_name(), patchMenuReq.getModify_menu_price(), patchMenuReq.getModify_menu_sale_chk(), patchMenuReq.getModify_menu_info(),menu_idx};

        return this.jdbcTemplate.update(modifyMenuQuery,modifyMenuParams);
//
//        return this.jdbcTemplate.update(modifyMenuQuery,modifyMenuParams);
//        String lastInsertIdQuery = "select last_insert_id()";
//        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

//     메뉴 재료 수정
    public int modifyIngrdt(int menu_idx,PatchIngredientReq patchIngredientReq){
        String modifyIngrdtQuery = "insert into store_menu_ingredient (ingrdt_menu_idx,ingrdt_name) values(?,?)";
        Object[] modifyIngrdtParams = new Object[]{menu_idx,patchIngredientReq.getModify_ingrdt_name()};

        return this.jdbcTemplate.update(modifyIngrdtQuery,modifyIngrdtParams);
    }

    //메뉴 재료 비활성화
    public int deleteIngredient(int menu_idx){
        String deleteIngredientQuery = "update store_menu_ingredient set ingrdt_status = 'INACTIVE' where ingrdt_menu_idx = ? ";
        return this.jdbcTemplate.update(deleteIngredientQuery,menu_idx);
    }




    // 메뉴 판매여부 수정
    public int modifySaleChk(int menu_idx,PatchMenuSaleReq patchMenuSaleReq){
        String modifySaleChkQuery = "update store_menu set menu_sale_chk = ? where menu_idx = ? ";
        Object[] modifySaleChkParams = new Object[]{patchMenuSaleReq.getModify_menu_sale_chk(), menu_idx};

        return this.jdbcTemplate.update(modifySaleChkQuery,modifySaleChkParams);
    }


    // 카테고리명 수정
    public int modifyCategory(PatchCategoryReq patchCategoryReq){
        String modifyCategoryQuery = "update store_category set category_name = ? where category_idx = ?";
        Object[] modifyCategoryParams = new Object[]{patchCategoryReq.getModify_category_name(), patchCategoryReq.getCategory_idx()};

        return this.jdbcTemplate.update(modifyCategoryQuery,modifyCategoryParams);
    }

    // 메뉴 삭제
    public int deleteMenu(int menu_idx){
        String deleteMenuQuery = "update store_menu set menu_status = 'INACTIVE' where menu_idx = ? and where menu_status='ACTIVE'";
        int deleteMenuParams = menu_idx;

        return this.jdbcTemplate.update(deleteMenuQuery,deleteMenuParams);
    }

    // 메뉴 존재 여부 확인
    public int checkMenuExist(int menu_idx){
        String checkMenuExistQuery = "select exists(select menu_idx from store_menu where menu_idx = ?)";
        int checkMenuExistParams = menu_idx;
        return this.jdbcTemplate.queryForObject(checkMenuExistQuery,
                int.class,
                checkMenuExistParams);

    }

    // 카테고리 존재 여부 확인
    public int checkCategoryExist(int category_idx){
        String checkCategoryExistQuery = "select exists(select category_idx from store_category where category_idx = ?)";
        int checkCategoryExistParams = category_idx;
        return this.jdbcTemplate.queryForObject(checkCategoryExistQuery,
                int.class,
                checkCategoryExistParams);

    }

    // <10>
    public int checkStoreExist(int store_idx){
        String checkStoreExistQuery = "select exists(select store_idx from store where store_idx = ?)";
        int checkStoreExistParams = store_idx;
        return this.jdbcTemplate.queryForObject(checkStoreExistQuery,
                int.class,
                checkStoreExistParams);
    }

    // 해당 가게의 메뉴인지 확인
    public int checkStoreMenuExist(int store_idx,int menu_idx){
        String checkStoreMenuExistQuery = "select exists(select menu_idx from store_menu where menu_idx = ? and menu_store_idx=?)";
        Object[] checkStoreMenuExistParams = new Object[]{menu_idx,store_idx};
        return this.jdbcTemplate.queryForObject(checkStoreMenuExistQuery,
                int.class,
                checkStoreMenuExistParams);
    }

    // 해당 가게의 카테고리인지 확인
    public int checkStoreCategoryExist(int store_idx,int category_idx){
        String checkStoreCategoryExistQuery = "select exists(select category_idx from store_category where category_idx = ? and category_store_idx=?)";
        Object[] checkStoreCategoryExistParams = new Object[]{category_idx,store_idx};
        return this.jdbcTemplate.queryForObject(checkStoreCategoryExistQuery,
                int.class,
                checkStoreCategoryExistParams);
    }



}


