package com.nbf.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-25
 * Time: 下午10:13
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private String username;
    private String password;
    private int age;

    public User() {
    }

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("user = {");
        sb.append("username:").append(this.username==null?"":this.username)
                .append("password:").append(this.password==null?"":this.password)
                .append("age:").append(this.age).append('}');
        return sb.toString();
    }
}
