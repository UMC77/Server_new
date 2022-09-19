package com.example.demo.src.menu;


import com.example.demo.config.BaseException;

import com.example.demo.src.menu.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class MenuService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuDao menuDao;
    private final MenuProvider menuProvider;
    private final JwtService jwtService;


    @Autowired
    public MenuService(MenuDao menuDao, MenuProvider menuProvider, JwtService jwtService) {
        this.menuDao = menuDao;
        this.menuProvider = menuProvider;
        this.jwtService = jwtService;

    }

    /* 메뉴 등록 */
    public PostMenuRes createMenu(int category_idx, PostMenuReq postMenuReq) throws BaseException {

        //카테고리 존재 유무 check
        if(menuProvider.checkCategoryExist(category_idx) ==0){
            throw new BaseException(MENU_EMPTY_CATEGORY_IDX);
        }

        try{
            int menu_idx = menuDao.createMenu(category_idx, postMenuReq);

            for(int i=0; i< postMenuReq.getMenu_ingrdt().size(); i++) {
                if (postMenuReq.getMenu_ingrdt().size()==0) break;
                menuDao.insertIngrdt(menu_idx, postMenuReq.getMenu_ingrdt().get(i));  //**dao에만 있는 메소드
            }
            return new PostMenuRes(menu_idx);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /* 카테고리 등록 */
    public PostCategoryRes addCategory(int store_idx) throws BaseException {

        //가게 존재 유무 check
        if(menuProvider.checkStoreExist(store_idx) ==0){
            throw new BaseException(STORE_EMPTY_STORE_IDX);
        }

        try{
            int category_idx = menuDao.addCategory(store_idx);

            return new PostCategoryRes(category_idx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /* 메뉴 수정 */
    public void modifyMenu(int menu_idx, PatchMenuReq patchMenuReq) throws BaseException {

        //메뉴 존재 유무 check
        if(menuProvider.checkMenuExist(menu_idx) ==0){
            throw new BaseException(MENU_EMPTY_MENU_IDX);
        }

        try{
            int res=menuDao.modifyMenu(menu_idx,patchMenuReq);

            menuDao.deleteIngredient(menu_idx);
            System.out.println(menu_idx);

            for(int i=0; i< patchMenuReq.getModify_menu_ingrdt().size(); i++) {
                if (patchMenuReq.getModify_menu_ingrdt().size()==0) break;
                menuDao.modifyIngrdt(menu_idx, patchMenuReq.getModify_menu_ingrdt().get(i));  //**dao에만 있는 메소드
            }

            if(res == 0){
                throw new BaseException(MODIFY_FAIL_MENU);
            }
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /* 메뉴 판매여부 수정 */
    public void modifySaleChk(int menu_idx, PatchMenuSaleReq patchMenuSaleReq) throws BaseException {


        //메뉴 존재 유무 check
        if(menuProvider.checkMenuExist(menu_idx) ==0){
            throw new BaseException(MENU_EMPTY_MENU_IDX);
        }


        try{
            int result = menuDao.modifySaleChk(menu_idx,patchMenuSaleReq);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_MENU);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /* 카테고리명 수정 */
    public void modifyCategory(PatchCategoryReq patchCategoryReq) throws BaseException {

        try{
            int result = menuDao.modifyCategory(patchCategoryReq);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_CATEGORY);
            }
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }



    /* 메뉴 삭제 */
    public void deleteMenu(int menu_idx) throws BaseException {


        //메뉴 존재 유무 check
        if(menuProvider.checkMenuExist(menu_idx) ==0){
            throw new BaseException(MENU_EMPTY_MENU_IDX);
        }



        try{
            int result = menuDao.deleteMenu(menu_idx);
            if(result == 0){
                throw new BaseException(DELETE_FAIL_MENU);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
