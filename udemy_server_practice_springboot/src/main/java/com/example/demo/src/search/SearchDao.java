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

    }
}
