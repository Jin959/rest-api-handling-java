package org.example;

import org.example.dto.GetUser;
import org.example.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final UserService userService = new UserService();
    public static void main(String[] args) {
        List<GetUser> userList = userService.getUserList();

        List<String> usernameList = userList.stream().map(user -> user.getName())
                .collect(Collectors.toList());

        System.out.println("--- 정렬 전 ---");
        usernameList.forEach(System.out::println);
        System.out.println("--- 정렬 ---");
        usernameList.stream().sorted((username1, username2) -> username1.compareTo(username2))
                .forEach(username -> System.out.println(username));
    }
}