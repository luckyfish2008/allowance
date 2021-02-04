package com.xxcw.allowance.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel(description = "登录表单")
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean state;

    public User() {
    }


    public int getId() {
        return id;
    }

    @ApiModelProperty(value="登录账号",name="username",required = true,example = "admin")
    @NotBlank(message="账号不允许为空，请输入")
    public String getUsername() {
        return username;
    }
    @ApiModelProperty(value="登录密码",name="password",required = true,example = "123456")
    @NotBlank(message="密码不允许为空，请输入")
    public String getPassword() {
        return password;
    }

    public boolean isState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", state=" + state +
                '}';
    }
}
