package com.spearbothy.ytime.netimpl.entity;

/**
 * Created by Alex_MaHao on 2017/5/31.
 */
public class User {


    /**
     * id : db6c5b0f-55fa-4d1b-859a-f484eccba20c
     * name : 123213
     * nickName : null
     * mobile : null
     * password : 3a50a47a570b54cb1aea39fb89c1775b
     * openid : null
     * accessToken : null
     * createTime : 2017-05-31 22:19:05.0
     * updateTime : 2017-05-31 22:19:05.0
     */

    private String id;
    private String name;
    private String nickName;
    private String mobile;
    private String password;
    private String openid;
    private String accessToken;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", openid='" + openid + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
