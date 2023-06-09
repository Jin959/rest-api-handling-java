package org.example.service;

import com.google.gson.*;
import org.example.dto.GetUser;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final Gson gson = new Gson();
    private static final RestApiService restApiService = new RestApiService();
    private static final String usersUrl = "https://jsonplaceholder.typicode.com/users";
    public List<GetUser> getUserList() {
        List<GetUser> getUserList = new ArrayList<>();
        try {
            String userResponse = restApiService.getJsonResponse(usersUrl);

            JsonArray userJsonArray = gson.fromJson(userResponse, JsonArray.class);

            for (JsonElement e : userJsonArray) {
                GetUser user = gson.fromJson(e, GetUser.class);
                getUserList.add(user);
            }
            return getUserList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getUserList;
    }
}
