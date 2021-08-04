package com.dhkim.session_login.user.domain;

import lombok.Data;

@Data
public class User{

    private int     adminNo;           // 관리자 번호
    private String  adminID;           // 관리자 아이디
    private String  adminPwd;          // 관리자 비밀번호
    private Short   menuRoleNo;        // 관리자 메뉴 권한 번호

}
