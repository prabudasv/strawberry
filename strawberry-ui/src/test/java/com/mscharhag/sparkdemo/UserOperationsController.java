package com.mscharhag.sparkdemo;

import com.google.gson.Gson;

import static com.mscharhag.sparkdemo.JsonUtil.json;
import static spark.Spark.*;


/**
 * Created by prabvara on 11/24/2016.
 */
public class UserOperationsController {

    public  UserOperationsController ()
    {
        UserService userService = new UserService();
        //UserService updateUserService = new UserService();
        Gson gson = new Gson();
        staticFiles.location("/public");
        get("/", (rq, res) -> "INDEX");
        get("/users", (req, res) -> userService.getAllUsers(), json());

        delete("/users/:id", (req, res) -> {
            String jsonBody = req.body();
            User user = gson.fromJson(jsonBody, User.class);
            System.out.println("JSON body for Delete Users is : " +jsonBody);
            userService.deleteUser(user.getId(), user.getName(), user.getEmail());
            return jsonBody;
        }, json());

        put("/users/:id", (req, res) -> {
            String jsonBody = req.body();
            User user = gson.fromJson(jsonBody, User.class);
            System.out.println("JSON body for PUT Users is : " +jsonBody);
            userService.updateUser(user.getId(), user.getName(), user.getEmail());
            return jsonBody;
        }, json());

        post("/users", (req, res) -> {
            String jsonBody = req.body();
            User user = gson.fromJson(jsonBody, User.class);
            System.out.println("JSON body for POST Users is : " +jsonBody);
            userService.createUser(user.getName(), user.getEmail());
            return jsonBody;
        }, json());


        exception(Exception.class, (ex, req, res) -> ex.printStackTrace());
    }

}
