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

    // [POST]
    // /ceo
//    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_CEO_INVALID_ID(false, 2020, "아이디는 공백 없는 영대소문자 및 숫자만 허용됩니다."),
    POST_CEO_INVALID_ID_LEN(false, 2021, "아이디는 10자 이내만 허용됩니다."),
    POST_CEO_INVALID_PWD(false, 2022, "비밀번호는 공백 없는 영대소문자 및 숫자만 허용됩니다."),
    POST_CEO_INVALID_PWD_LEN(false, 2023, "비밀번호는 8자 이상만 허용됩니다."),
    POST_CEO_EMPTY_ID(false, 2024, "아이디를 입력해주세요."),
    POST_CEO_EMPTY_STORE_NUM(false, 2025, "사업자등록번호를 입력해주세요."),
    POST_CEO_EMPTY_CEO_PHONE(false, 2026, "핸드폰 번호를 입력해주세요."),
    POST_CEO_EMPTY_CEO_NAME(false, 2027, "사장님 이름을 입력해주세요."),
    POST_CEO_EMPTY_STORE_NAME(false, 2028, "상호명을 입력해주세요."),
    POST_CEO_EMPTY_STORE_PHONE(false, 2029, "매장 전화 번호를 입력해주세요."),
    POST_CEO_EMPTY_ADDRESS(false, 2030, "주소를 입력해주세요."),
    POST_CEO_EMPTY_ADDRESS_DETAIL(false, 2031, "상세 주소를 입력해주세요."),
    POST_CEO_EMPTY_PWD(false, 2032, "비밀번호를 입력해주세요."),
    POST_CEO_EXISTS_STORE_NUM(false,2033,"중복된 사업자등록번호입니다."),
    POST_CEO_EXISTS_CEO_ID(false,2034,"중복된 아이디입니다."),
    POST_CEO_EXISTS_CEO_PHONE(false,2035,"중복된 핸드폰 번호입니다."),


    // /store
    POST_STORE_EMPTY_STORE_TIME(false, 2036, "매장 운영시간을 입력해주세요."),
    POST_STORE_EMPTY_STORE_HOLIDAY(false, 2037, "매장 휴무일을 입력해주세요."),
    POST_STORE_EMPTY_IMGRUL(false, 2038, "매장 사진을 등록해주세요."),

    // /menu
    POST_MENU_EMPTY_IMGRUL(false, 2039, "메뉴 사진을 등록해주세요."),
    POST_MENU_EMPTY_MENU_NAME(false, 2040, "메뉴 이름을 등록해주세요."),
    POST_MENU_EMPTY_MENU_PRICE(false, 2041, "메뉴 가격을 등록해주세요."),
//    POST_MENU_EMPTY_SALE_CHK(false, 2042, "메뉴 판매여부를 등록해주세요."),


    //[PATCH]
    // /ceo
    PATCH_CEO_EMPTY_MODIFY_PWD(false,2050,"수정할 사장 비밀번호를 입력해주세요."),
    PATCH_CEO_INVALID_MODIFY_PWD_LEN(false,2051,"수정할 사장 비밀번호는 8자 이상만 허용됩니다."),
    PATCH_CEO_INVALID_MODIFY_PWD(false,2052,"수정할 사장 비밀번호는 영대소문자 및 숫자만 허용됩니다."),

    // /store
    PATCH_STORE_EMPTY_MODIFY_STORE_NAME(false,2053,"수정할 상호명을 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_IMGRUL(false,2054,"수정한 사진을 등록해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_NUM(false,2055,"수정할 사업자 등록번호를 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_ADDRESS(false,2056,"수정할 가게 주소를 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_PHONE(false,2057,"수정할 가게 전화번호를 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_TIME(false,2058,"수정할 운영일을 입력해주세요."),
    PATCH_STORE_EMPTY_MODIFY_STORE_HOLI(false,2059,"수정할 휴무일을 입력해주세요."),

    // /menu
    PATCH_MENU_EMPTY_MODIFY_MENU_IMGURL(false,2060,"수정할 메뉴 이미지를 등록해주세요."),
    PATCH_MENU_EMPTY_MODIFY_MENU_NAME(false,2061,"수정할 메뉴명을 입력해주세요."),
    PATCH_MENU_EMPTY_MODIFY_MENU_PRICE(false,2062,"수정할 메뉴 가격을 입력해주세요."),



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


    PASSWORD_ENCRYPTION_ERROR(false, 4040, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4041, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
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
