package com.example.demo.src.search;

import com.example.demo.src.search.model.SearchRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SearchDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate=jdbcTemplate;
    }

    //검색
    public List<SearchRes> search(String search_word){
        String getSearch = "select p.picture_url, s.store_name, s.store_address, AVG(r.review_score), COUNT(r.review_score), s.store_reserve\n" +
                "    FROM store_picture as p, store as s, store_review as r\n" +
                "WHERE s.store_name = ? AND p.picture_store_name = ? AND r.review_store_name = ?";

        return this.jdbcTemplate.query(getSearch,
                (rs, rowNum) -> new SearchRes(
                        rs.getString("picture_url"),
                        rs.getString("store_name"),
                        rs.getString("store_address"),
                        rs.getFloat("review_score"),
                        rs.getInt("review_cnt"),
                        rs.getString("store_reserve")
        ), search_word);
    }

}
