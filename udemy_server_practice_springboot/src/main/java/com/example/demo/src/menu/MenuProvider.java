//package com.example.demo.src.menu;
//
//
//import com.example.demo.config.BaseException;
//import com.example.demo.src.menu.model.*;
//import com.example.demo.utils.JwtService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//import static com.example.demo.config.BaseResponseStatus.*;
//
////Provider : Read의 비즈니스 로직 처리
//@Service
//public class MenuProvider {
//
//    private final MenuDao menuDao;
//    private final JwtService jwtService;
//
//
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    public MenuProvider(MenuDao menuDao, JwtService jwtService) {
//        this.menuDao = menuDao;
//        this.jwtService = jwtService;
//    }
//
//    //메뉴 존재여부 확인
//    public int checkMenuExist(int meu_idx) throws BaseException{
//        try{
//            return menuDao.checkMenuExist(meu_idx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    //가게 존재 여부 확인
//    public int checkStoreExist(int store_idx) throws BaseException{
//        try{
//            return menuDao.checkStoreExist(store_idx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    //해당 가게의 메뉴인지 확인
//    public int checkStoreMenuExist(int store_idx,int menu_idx) throws BaseException{
//        try{
//            return menuDao.checkStoreMenuExist(store_idx,menu_idx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
////
//    //메뉴 정보 조회 - 수정 ver.
//    public GetMenuModifyRes getModifyMenu(int store_idx) throws BaseException {
//        //store 존재여부 확인
//        if(checkStoreExist(store_idx) ==0){
//            throw new BaseException(STORE_EMPTY_STORE_IDX);
//        }
//
//        try{
//            GetMenuModifyRes getMenuModifyRes = menuDao.getModifyMenu(store_idx);
//            return getMenuModifyRes;
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//
//    }
//
//    //메뉴 리스트 조회
//    public List<GetMenuRes> getMenu(int store_idx) throws BaseException {
//        //store 존재여부 확인
//        if(checkStoreExist(store_idx) ==0){
//            throw new BaseException(STORE_EMPTY_STORE_IDX);
//        }
//
//        try{
//            List<GetMenuRes> getMenu = menuDao.getMenu(store_idx);
//            return getMenu;
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//
//    }
//
//
//
//
//}
