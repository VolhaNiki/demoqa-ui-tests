package rest.utils;

import rest.model.user.UserCreate;

public class UserGenerator {
    public static UserCreate createUser(){
        return UserCreate.builder()
                .username("simple")
                .email("automation")
                .build();
    }
}
