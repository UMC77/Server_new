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

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    //ceo
    CEO_EMPTY_CEO_ID(false, 2011, "사장 아이디 값을 확인해주세요."),
    CEO_EMPTY_CEO_PWD(false, 2012, "사장 비밀번호 값을 확인해주세요."),
    CEO_ERROR_CEO_PWD(false, 2013, "비밀번호가 다릅니다."),

    //store
    STORE_EMPTY_STORE_IDX(false, 2014, "가게 인덱스 값을 확인해주세요."),

    // [POST] /ceo
//    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_CEO_INVALID_ID(false, 2016, "아이디는 공백 없는 영대소문자 및 숫자만 허용됩니다."),
    POST_CEO_INVALID_ID_LEN(false, 2017, "아이디는 10자 이내만 허용됩니다."),
    POST_CEO_INVALID_PWD(false, 2018, "비밀번호는 공백 없는 영대소문자 및 숫자만 허용됩니다."),
    POST_CEO_INVALID_PWD_LEN(false, 2019, "비밀번호는 8자 이상만 허용됩니다."),
    POST_CEO_EMPTY_ID(false, 2020, "아이디를 입력해주세요."),
    POST_CEO_EMPTY_STORE_NUM(false, 2021, "사업자등록번호를 입력해주세요."),
    POST_CEO_EMPTY_CEO_PHONE(false, 2022, "핸드폰 번호를 입력해주세요."),
    POST_CEO_EMPTY_CEO_NAME(false, 2023, "사장님 이름을 입력해주세요."),
    POST_CEO_EMPTY_STORE_NAME(false, 2024, "상호명을 입력해주세요."),
    POST_CEO_EMPTY_STORE_PHONE(false, 2025, "매장 전화 번호를 입력해주세요."),
    POST_CEO_EMPTY_ADDRESS(false, 2026, "주소를 입력해주세요."),
    POST_CEO_EMPTY_PWD(false, 2027, "비밀번호를 입력해주세요."),
    POST_CEO_EXISTS_STORE_NUM(false,2028,"중복된 사업자등록번호입니다."),
    POST_CEO_EXISTS_CEO_ID(false,2029,"중복된 아이디입니다."),
    POST_CEO_EXISTS_CEO_PHONE(false,2030,"중복된 핸드폰 번호입니다."),

    //[PATCH]
    // /ceo
    PATCH_CEO_EMPTY_MODIFY_PWD(false,2050,"수정할 사장 비밀번호를 입력해주세요."),
    // /store
    PATCH_STORE_EMPTY_MODIFY_STORE_NAME(false,2051,"수정할 상호명을 입력해주세요."),



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

    //[PATCH] /ceo/{ceoIdx}
    MODIFY_FAIL_CEO_PWD(false,4015,"사장 비밀번호 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


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
