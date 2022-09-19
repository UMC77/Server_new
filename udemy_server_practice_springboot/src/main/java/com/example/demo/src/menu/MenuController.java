package com.example.demo.src.menu;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.menu.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MenuProvider menuProvider;
    @Autowired
    private final MenuService menuService;
    @Autowired
    private final JwtService jwtService;

    public MenuController(MenuProvider menuProvider, MenuService menuService, JwtService jwtService){
        this.menuProvider = menuProvider;
        this.menuService = menuService;
        this.jwtService = jwtService;
    }

    /* 메뉴 개수 조회 */
    @ResponseBody
    @GetMapping("/{storeIdx}")
    public BaseResponse<GetMenuCntRes> getMenuCnt(@PathVariable("storeIdx") int store_idx) {
        try{

            GetMenuCntRes getMenuCnt=menuProvider.getMenuCnt(store_idx);

            return new BaseResponse<>(getMenuCnt);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 메뉴 조회 - 수정 ver. */
    @ResponseBody
    @GetMapping("/{menuIdx}/viewmodi")
    public BaseResponse<GetMenuModifyRes> getModifyMenu(@PathVariable("menuIdx") int menu_idx) {
        try{
            GetMenuModifyRes getModifyMenu=menuProvider.getModifyMenu(menu_idx);

            return new BaseResponse<>(getModifyMenu);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 전체 메뉴 조회 */
    @ResponseBody
    @GetMapping("/{storeIdx}/{viewType}")
    public BaseResponse<List<GetCategoryMenuRes>> getMenu(@PathVariable("storeIdx") int store_idx,@PathVariable("viewType") String view_type) {
        try{
            List<GetCategoryMenuRes> getCategoryMenu=menuProvider.getCategoryMenu(store_idx,view_type);

            return new BaseResponse<>(getCategoryMenu);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 메뉴 검색 */
    @ResponseBody
    @GetMapping("/search/{storeIdx}")
    public BaseResponse<List<GetMenuRes>> getSearchMenu(@PathVariable("storeIdx") int store_idx, @RequestBody GetSearchReq getSearchReq) {

        //키워드 공백 check
        if(getSearchReq.getKeyword()== null){
            return new BaseResponse<>(MENU_EMPTY_SEARCH_KEYWORD);
        }

        try{

            List<GetMenuRes> getSearchMenu=menuProvider.getSearchMenu(store_idx,getSearchReq);

            return new BaseResponse<>(getSearchMenu);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /* 카테고리 조회 */
    @ResponseBody
    @GetMapping("/category/{storeIdx}")
    public BaseResponse<List<GetCategoryRes>> getCategory(@PathVariable("storeIdx") int store_idx) {

        try{

            List<GetCategoryRes> getCategory=menuProvider.getCategory(store_idx);

            return new BaseResponse<>(getCategory);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /* 메뉴 등록 */
    @ResponseBody
    @PostMapping("/{categoryIdx}/createMenu")
    public BaseResponse<PostMenuRes> createMenu(@PathVariable("categoryIdx") int category_idx, @RequestBody PostMenuReq postMenuReq) {

        //메뉴 대표 사진 공백 check
        if(postMenuReq.getMenu_img_url()== null){
            return new BaseResponse<>(POST_MENU_EMPTY_IMGRUL);
        }

        //메뉴명 공백 check
        if(postMenuReq.getMenu_name() == null)
            return new BaseResponse<>(POST_MENU_EMPTY_MENU_NAME);

        //가격 공백 check
        if(postMenuReq.getMenu_price() == 0)
            return new BaseResponse<>(POST_MENU_EMPTY_MENU_PRICE);

        try {
//            int storeIdxByJwt = jwtService.getStoreIdx();
            PostMenuRes postMenuRes = menuService.createMenu(category_idx,postMenuReq);
            return new BaseResponse<>(postMenuRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 카테고리 등록 */
    @ResponseBody
    @PostMapping("/{storeIdx}/addCategory")
    public BaseResponse<PostCategoryRes> addCategory(@PathVariable("storeIdx") int store_idx) {

        try {

            PostCategoryRes postCategoryRes = menuService.addCategory(store_idx);
            return new BaseResponse<>(postCategoryRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /* 메뉴 수정 */
    @ResponseBody
    @PatchMapping("/{menuIdx}/modify")
    public BaseResponse<String> modifyMenu(@PathVariable("menuIdx") int menu_idx, @RequestBody PatchMenuReq patchMenuReq){

        //메뉴 대표 사진 공백 check
        if(patchMenuReq.getModify_menu_img_url()== null){
            return new BaseResponse<>(PATCH_MENU_EMPTY_MODIFY_MENU_IMGURL);
        }

        //메뉴명 공백 check
        if(patchMenuReq.getModify_menu_name() == null)
            return new BaseResponse<>(PATCH_MENU_EMPTY_MODIFY_MENU_NAME);

        //가격 공백 check
        if(patchMenuReq.getModify_menu_price() == 0)
            return new BaseResponse<>(PATCH_MENU_EMPTY_MODIFY_MENU_PRICE);


        try {

            menuService.modifyMenu(menu_idx,patchMenuReq);
            String str = "메뉴가 성공적으로 변경되었습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /* 메뉴 판매여부 수정 */
    @ResponseBody
    @PatchMapping("/{menuIdx}/modifySale")
    public BaseResponse<String> modifySaleChk(@PathVariable("menuIdx") int menu_idx, @RequestBody PatchMenuSaleReq patchMenuSaleReq){

        try {


            menuService.modifySaleChk(menu_idx,patchMenuSaleReq);
            String str = "메뉴 판매여부가 성공적으로 변경되었습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /* 카테고리명 수정 */
    @ResponseBody
    @PatchMapping("modifyCategory")
    public BaseResponse<String> modifyCategory(@RequestBody PatchCategoryReq patchCategoryReq){

        try {

            menuService.modifyCategory(patchCategoryReq);
            String str = "카테고리명이 성공적으로 변경되었습니다.";
            return new BaseResponse<>(str);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 메뉴 삭제
    @ResponseBody
    @PatchMapping("/{menuIdx}/delete")
    public BaseResponse<String> deleteMenu(@PathVariable("menuIdx") int menu_idx){
        try {

            menuService.deleteMenu(menu_idx);

            String result = "삭제되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }




}
