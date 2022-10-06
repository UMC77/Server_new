package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    INVALID_CEO_JWT(false,2004,"권한이 없는 사장유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    //ceo
    CEO_EMPTY_CEO_ID(false, 2011, "사장 아이디 값을 확인해주세요."),
    CEO_EMPTY_CEO_PWD(false, 2012, "사장 비밀번호 값을 확인해주세요."),
    CEO_ERROR_CEO_PWD(false, 2013, "비밀번호가 다릅니다."),
    CEO_EMPTY_PWD_CHK(false, 2014, "확인을 위해 비밀번호를 한번 더 입력해주세요."),

    //store
    STORE_EMPTY_STORE_IDX(false, 2015, "가게 인덱스 값을 확인해주세요."),
    STORE_EMPTY_CEO_STORE(false, 2016, "사장에게 해당하는 가게가 없습니다."),

    // /menu
    MENU_EMPTY_MENU_IDX(false, 2017, "메뉴 인덱스 값을 확인해주세요."),
    MENU_EMPTY_STORE_MENU(false, 2018, "가게에 해당하는 메뉴가 없습니다."),
    MENU_EMPTY_CATEGORY_IDX(false, 2019, "카테고리 인덱스 값을 확인해주세요."),
    MENU_EMPTY_STORE_CATEGORY(false, 2020, "가게에 해당하는 카테고리가 없습니다."),
    MENU_EMPTY_SEARCH_KEYWORD(false, 2021, "검색어를 입력해주세요"),


    // /reservation
    RESERVATION_EMPTY_RESERVATION_IDX(false, 2022, "예약 인덱스 값을 확인해주세요."),
    RESERVATION_EMPTY_STORE_RESERVATION(false, 2023, "가게에 해당 예약이 없습니다."),

    // /review
    REVIEW_EMPTY_REVIEW_IDX(false, 2024, "리뷰 인덱스 값을 확인해주세요."),
    REVIEW_EMPTY_STORE_REVIEW(false, 2025, "가게에 해당 리뷰가 없습니다."),


    // [POST]
    // /ceo
//    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_CEO_INVALID_ID(false, 2040, "아이디는 공백 없는 영대소문자 및 숫자만 허용됩니다."),
    POST_CEO_INVALID_ID_LEN(false, 2041, "아이디는 10자 이내만 허용됩니다."),
    POST_CEO_INVALID_PWD(false, 2042, "비밀번호는 공백 없는 영대소문자 및 숫자만 허용됩니다."),
    POST_CEO_INVALID_PWD_LEN(false, 2043, "비밀번호는 8자 이상만 허용됩니다."),
    POST_CEO_EMPTY_ID(false, 2044, "아이디를 입력해주세요."),
    POST_CEO_EMPTY_STORE_NUM(false, 2045, "사업자등록번호를 입력해주세요."),
    POST_CEO_EMPTY_CEO_PHONE(false, 2046, "핸드폰 번호를 입력해주세요."),
    POST_CEO_EMPTY_CEO_NAME(false, 2047, "사장님 이름을 입력해주세요."),
    POST_CEO_EMPTY_STORE_NAME(false, 2048, "상호명을 입력해주세요."),
    POST_CEO_EMPTY_STORE_PHONE(false, 2049, "매장 전화 번호를 입력해주세요."),
    POST_CEO_EMPTY_ADDRESS(false, 2050, "주소를 입력해주세요."),
    POST_CEO_EMPTY_ADDRESS_DETAIL(false, 2051, "상세 주소를 입력해주세요."),
    POST_CEO_EMPTY_PWD(false, 2052, "비밀번호를 입력해주세요."),
    POST_CEO_EXISTS_STORE_NUM(false,2053,"중복된 사업자등록번호입니다."),
    POST_CEO_EXISTS_CEO_ID(false,2054,"중복된 아이디입니다."),
    POST_CEO_EXISTS_CEO_PHONE(false,2055,"중복된 핸드폰 번호입니다."),


    // /store
    POST_STORE_EMPTY_STORE_TIME(false, 2056, "매장 운영시간을 입력해주세요."),
    POST_STORE_EMPTY_STORE_HOLIDAY(false, 2057, "매장 휴무일을 입력해주세요."),
    POST_STORE_EMPTY_IMGRUL(false, 2058, "매장 사진을 등록해주세요."),

    // /menu
    POST_MENU_EMPTY_IMGRUL(false, 2059, "메뉴 사진을 등록해주세요."),
    POST_MENU_EMPTY_MENU_NAME(false, 2060, "메뉴 이름을 등록해주세요."),
    POST_MENU_EMPTY_MENU_PRICE(false, 2061, "메뉴 가격을 등록해주세요."),
//    POST_MENU_EMPTY_SALE_CHK(false, 2042, "메뉴 판매여부를 등록해주세요."),



    //[PATCH]
    // /ceo
    PATCH_CEO_EMPTY_MODIFY_PWD(false,2080,"수정할 사장 비밀번호를 입력해주세요."),
    PATCH_CEO_INVALID_MODIFY_PWD_LEN(false,2081,"수정할 사장 비밀번호는 8자 이상만 허용됩니다."),
    PATCH_CEO_INVALID_MODIFY_PWD(false,2082,"수정할 사장 비밀번호는 영대소문자 및 숫자만 허용됩니다."),

    // /store
    PATCH_STORE_EMPTY_MODIFY_STORE_NAME(false,2083,"수정할 상호명을 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_IMGRUL(false,2084,"수정한 사진을 등록해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_NUM(false,2085,"수정할 사업자 등록번호를 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_ADDRESS(false,2086,"수정할 가게 주소를 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_PHONE(false,2087,"수정할 가게 전화번호를 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_TIME(false,2088,"수정할 운영일을 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_HOLI(false,2089,"수정할 휴무일을 입력해주세요."),

    // /menu
    PATCH_MENU_EMPTY_MODIFY_MENU_IMGURL(false,2090,"수정할 메뉴 이미지를 등록해주세요."),
    PATCH_MENU_EMPTY_MODIFY_MENU_NAME(false,2091,"수정할 메뉴명을 입력해주세요."),
    PATCH_MENU_EMPTY_MODIFY_MENU_PRICE(false,2092,"수정할 메뉴 가격을 입력해주세요."),

    // /review
    PATCH_REVIEW_EMPTY_MODIFY_REPLY(false,2093,"댓글을 입력해주세요."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH]
    // /ceo/{ceo_idx}
    MODIFY_FAIL_CEO_PWD(false,4020,"사장 비밀번호 수정 실패"),

    // /store/{store_idx}
    MODIFY_FAIL_STORE(false,4021,"가게 수정 실패"),

    // /menu/{menu_idx}
    MODIFY_FAIL_MENU(false,4022,"메뉴 수정 실패"),
    DELETE_FAIL_MENU(false,4023,"메뉴 삭제 실패"),

    // /menu/{category_idx}
    MODIFY_FAIL_CATEGORY(false,4024,"카테고리 수정 실패"),

    //  /review
    MODIFY_FAIL_REVIEW_REPLY(false,4025,"리뷰 댓글 업데이트 실패"),
    DELETE_FAIL_REVIEW_REPLY(false,4026,"리뷰 댓글 삭제 실패"),


    PASSWORD_ENCRYPTION_ERROR(false, 4040, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4041, "비밀번호 복호화에 실패하였습니다."),


    // 5000 : 필요시 만들어서 쓰세요
    APP_USERS_EMPTY_USER_PASSWORD(false, 5001, "유저 비밀번호 값을 확인해주세요"),
    APP_USERS_ERROR_USER_PASSWORD(false, 5002, "비밀번호가 다릅니다."),
    APP_USERS_NOT_EXISTS_ID(false, 5003, "존재하지 않는 아이디 입니다."),

    // [POST] /users
    APP_POST_USERS_EMPTY_EMAIL(false, 5005, "이메일을 입력해주세요."),
    APP_POST_USERS_INVALID_EMAIL(false, 5006, "이메일 형식을 확인해주세요."),
    APP_POST_USERS_EXISTS_EMAIL(false,5007,"중복된 이메일입니다."),
    APP_POST_USERS_EMPTY_PASSWORD(false, 5008, "비밀번호를 입력해주세요."),
    APP_POST_USERS_EMPTY_NICKNAME(false, 5009, "닉네임을 입력해주세요."),
    APP_POST_USERS_EXISTS_NICKNAME(false, 5010, "중복된 닉네임 입니다."),
    APP_POST_USERS_EMPTY_PHONENUM(false, 5012, "전화번호를 입력해주세요."),



    APP_PATCH_USERS_EMPTY_MODIFY_PROFILE(false, 5013, "변경할 프로필을 선택해주세요."),
    SEARCH_WORD_EMPTY(false,5014,"검색어를 입력하세요."),

    REVIEW_STORE_EMPTY(false, 5015, "빵집 이름을 입력하세요."),
    REVIEW_SCORE_EMPTY(false, 5016, "별점을 입력하세요."),
    REVIEW_NICKNAME_EMPTY(false, 5017, "닉네임을 입력하세요."),
    RESERVE_RESERVE_EMPTY(false, 5018, "예약유형을 입력하세요."),
    RESERVE_MENU_EMPTY(false, 5019, "예약 메뉴를 입력하세요."),
    RESERVE_TIME_EMPTY(false, 5020, "예약 시간을 입력하세요.");

    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
