package com.example.demo.queries;

public class UserQueries {

    public final static String GET_USERS = "select * from users";
    public final static String GET_USER_BY_NAME = "select * from users where name = ?";
    public final static String CHECK_USER = "select * from users where name = ?";
    public final static String ADD_USER  = "INSERT into users(id, name) values (null, ?)";

}
