package com.example.demo.src.search;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.search.model.SearchReq;
import com.example.demo.src.search.model.SearchRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SearchProvider searchProvider;
    @Autowired
    private final SearchService searchService;
    @Autowired
    private final JwtService jwtService;


    public SearchController(SearchProvider searchProvider, SearchService searchService, JwtService jwtService) {
        this.searchProvider = searchProvider;
        this.searchService = searchService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<SearchRes>> search(@RequestBody SearchReq searchReq){
        try {
            if(searchReq.getSearch_word() == null)
                return new BaseResponse<>(BaseResponseStatus.SEARCH_WORD_EMPTY);
            List<SearchRes> searchRes = searchService.search(searchReq);
            return new BaseResponse<>(searchRes);
        } catch (BaseException exception)
        {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}

