package com.example.demo.src.menu;


import com.example.demo.config.BaseException;
import com.example.demo.src.menu.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class MenuProvider {

    private final MenuDao menuDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MenuProvider(MenuDao menuDao, JwtService jwtService) {
        this.menuDao = menuDao;
        this.jwtService = jwtService;
    }

    //메뉴 존재여부 확인
    public int checkMenuExist(int meu_idx) throws BaseException{
        try{
            return menuDao.checkMenuExist(meu_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //카테고리 존재여부 확인
    public int checkCategoryExist(int category_idx) throws BaseException{
        try{
            return menuDao.checkCategoryExist(category_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //가게 존재 여부 확인
    public int checkStoreExist(int store_idx) throws BaseException{
        try{
            return menuDao.checkStoreExist(store_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //해당 가게의 메뉴인지 확인
    public int checkStoreMenuExist(int store_idx,int menu_idx) throws BaseException{
        try{
            return menuDao.checkStoreMenuExist(store_idx,menu_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //해당 가게의 카테고리인지 확인
    public int checkStoreCategoryExist(int store_idx,int category_idx) throws BaseException{
        try{
            return menuDao.checkStoreCategoryExist(store_idx,category_idx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //메뉴 개수 조회
    public GetMenuCntRes getMenuCnt(int store_idx) throws BaseException {

        //store 존재여부 확인
        if(checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }


        try{
            GetMenuCntRes getMenuCntRes = menuDao.getMenuCnt(store_idx);
            return getMenuCntRes;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }

    }

//
    //메뉴 정보 조회 - 수정 ver.
    public GetMenuModifyRes getModifyMenu(int menu_idx) throws BaseException {

        //menu 존재여부 확인
        if(checkMenuExist(menu_idx) ==0){
            throw new BaseException(MENU_EMPTY_MENU_IDX);
        }



        try{
            GetMenuModifyRes getMenuModifyRes = menuDao.getModifyMenu(menu_idx);
            return getMenuModifyRes;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }

    }

    //메뉴 검색 리스트 조회
    public List<GetMenuRes> getSearchMenu(int store_idx, GetSearchReq getSearchReq) throws BaseException {

//        store 존재여부 확인
        if(checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        try{
            List<GetMenuRes> getSearchMenu = menuDao.getSearchMenu(getSearchReq);
            return getSearchMenu;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }

    }


    //메뉴 리스트 조회
    public List<GetCategoryMenuRes> getCategoryMenu(int store_idx,String view_type) throws BaseException {
        //store 존재여부 확인
        if(checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        try{
            List<GetCategoryMenuRes> getCategoryMenu = menuDao.getCategoryMenu(store_idx,view_type);
            return getCategoryMenu;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }

    }

    //카테고리 리스트 조회(검색시 활용)
    public List<GetCategoryRes> getCategory(int store_idx) throws BaseException {
        //store 존재여부 확인
        if(checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        try{
            List<GetCategoryRes> getCategory = menuDao.getCategory(store_idx);
            return getCategory;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }

    }




}
