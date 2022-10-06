package com.example.demo.src.search;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.search.model.SearchReq;
import com.example.demo.src.search.model.SearchRes;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchDao searchDao;
    private final SearchProvider searchProvider;
    private final JwtService jwtService;

    @Autowired
    public SearchService(SearchDao searchDao, SearchProvider searchProvider, JwtService jwtService){
        this.searchDao=searchDao;
        this.searchProvider=searchProvider;
        this.jwtService=jwtService;
    }

    public List<SearchRes> search(SearchReq searchReq) throws BaseException{
        try{
            List<SearchRes> searchRes = searchDao.search(searchReq.getSearch_word());
            return searchRes;
        } catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
