package com.dhkim.session_login.login.service;

import com.dhkim.session_login.login.dao.LoginDao;
import com.dhkim.session_login.login.domain.Login;
import com.dhkim.session_login.login.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    LoginDao loginDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Login loginUser = loginDao.selectUserById("login.selectUserById",userId);

        System.out.println("UserInfo : " + loginUser);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(loginUser == null){
            throw new UsernameNotFoundException("'" + userId + "' is not found.");
        } else{
            if(loginUser.getMenuRoleNo() == 1){
                authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getRoleCode()));
            } else {
                authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getRoleCode()));
            }
        }

        System.out.println("UserAuthority : " + authorities);

        return new User(loginUser.getAdminID(), loginUser.getAdminPwd(), authorities);
    }
}
