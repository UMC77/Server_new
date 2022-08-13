//package com.example.demo.src.menu;
//
//import com.example.demo.config.BaseException;
//import com.example.demo.config.BaseResponse;
//import com.example.demo.src.menu.model.*;
//import com.example.demo.utils.JwtService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import static com.example.demo.config.BaseResponseStatus.*;
//import static com.example.demo.utils.ValidationRegex.*;
//
//import java.awt.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/menu")
//public class MenuController {
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private final MenuProvider menuProvider;
//    @Autowired
//    private final MenuService menuService;
//    @Autowired
//    private final JwtService jwtService;
//
//    public MenuController(MenuProvider menuProvider, MenuService menuService, JwtService jwtService){
//        this.menuProvider = menuProvider;
//        this.menuService = menuService;
//        this.jwtService = jwtService;
//    }
//
//    /* 메뉴 조회 - 수정 ver. */
//    @RequestMapping
//    @GetMapping("")
//    public BaseResponse<GetMenuModifyRes> getModifyMenu() {
//        try{
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//            GetMenuModifyRes getModifyMenu=menuProvider.getModifyMenu(storeIdxByJwt);
//
//            return new BaseResponse<>(getModifyMenu);
//        } catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /* 메뉴 조회 */
//    @RequestMapping
//    @GetMapping("/view")
//    public BaseResponse<List<GetMenuRes>> getMenu() {
//        try{
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//            List<GetMenuRes> getMenu=menuProvider.getMenu(storeIdxByJwt);
//
//            return new BaseResponse<>(getMenu);
//        } catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//
//    /* 메뉴 등록 */
//    @RequestMapping
//    @PostMapping("/create")
//    public BaseResponse<PostMenuRes> createMenu(@RequestBody PostMenuReq postMenuReq) {
//
//        //메뉴 대표 사진 공백 check
//        if(postMenuReq.getMenu_img_url()== null){
//            return new BaseResponse<>(POST_MENU_EMPTY_IMGRUL);
//        }
//
//        //메뉴명 공백 check
//        if(postMenuReq.getMenu_name() == null)
//            return new BaseResponse<>(POST_MENU_EMPTY_MENU_NAME);
//
//        //가격 공백 check
//        if(postMenuReq.getMenu_price() == 0)
//            return new BaseResponse<>(POST_MENU_EMPTY_MENU_PRICE);
//
//        try {
//            int storeIdxByJwt = jwtService.getStoreIdx();
//            PostMenuRes postMenuRes = MenuService.createMenu(storeIdxByJwt,postMenuReq);
//            return new BaseResponse<>(postMenuRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//
//    /* 메뉴 수정 */
//    @ResponseBody
//    @PatchMapping("/{menuIdx}/modify")
//    public BaseResponse<String> modifyMenu(@PathVariable("menuIdx") int menu_idx, @RequestBody PatchMenuReq patchMenuReq){
//
//        //메뉴 대표 사진 공백 check
//        if(patchMenuReq.getModify_menu_img_url()== null){
//            return new BaseResponse<>(PATCH_MENU_EMPTY_MODIFY_MENU_IMGURL);
//        }
//
//        //메뉴명 공백 check
//        if(patchMenuReq.getModify_menu_name() == null)
//            return new BaseResponse<>(PATCH_MENU_EMPTY_MODIFY_MENU_NAME);
//
//        //가격 공백 check
//        if(patchMenuReq.getModify_menu_price() == 0)
//            return new BaseResponse<>(PATCH_MENU_EMPTY_MODIFY_MENU_PRICE);
//
//
//        try {
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//
//            menuService.modifyMenu(storeIdxByJwt,menu_idx,patchMenuReq);
//            String str = "메뉴가 성공적으로 변경되었습니다.";
//            return new BaseResponse<>(str);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /* 메뉴 판매여부 수정 */
//    @ResponseBody
//    @PatchMapping("/{menuIdx}/modifysale")
//    public BaseResponse<String> modifySaleChk(@PathVariable("menuIdx") int menu_idx, @RequestBody PatchMenuSaleReq patchMenuSaleReq){
//
//        try {
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//
//            menuService.modifySaleChk(storeIdxByJwt,menu_idx,patchMenuSaleReq);
//            String str = "메뉴 판매여부가 성공적으로 변경되었습니다.";
//            return new BaseResponse<>(str);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    // 메뉴 삭제
//    @ResponseBody
//    @PatchMapping("/{menuIdx}/delete")
//    public BaseResponse<String> deleteMenu(@PathVariable("menuIdx") int menu_idx){
//        try {
//
//            //jwt에서 idx 추출.
//            int storeIdxByJwt = jwtService.getStoreIdx();
//            menuService.deleteMenu(storeIdxByJwt,menu_idx);
//
//            String result = "삭제되었습니다.";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//
//
//
//}
