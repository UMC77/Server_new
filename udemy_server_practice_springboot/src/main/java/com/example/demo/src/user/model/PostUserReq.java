package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String user_id;
    private String user_pwd;
    private String user_phone;
    private String user_nickname;
    private String user_profile;
}
