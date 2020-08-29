package com.demo.model;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Objects;

public class User implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private String roles;

    private boolean enablel;

    private List<GrantedAuthority> authorities;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean getEnablel() {
        return enablel;
    }

    public void setEnablel(boolean enablel) {
        this.enablel = enablel;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 会话管理使用的是map注册  以key为用户信息对象所以需要重写HashCode和Equals
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof User ? StringUtils.equals(this.username, ((User) o).getUsername()) : false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
