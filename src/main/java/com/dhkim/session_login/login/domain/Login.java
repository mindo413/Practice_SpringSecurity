package com.dhkim.session_login.login.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
public class Login{

    private int     adminNo;           // 관리자 번호
    private String  adminID;           // 관리자 아이디
    private String  adminPwd;          // 관리자 비밀번호
    private Short   menuRoleNo;        // 관리자 메뉴 권한 번호
    private LocalDateTime regDate;     // 등록 일자

}
