package com.myshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lenovo on 2019/6/5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;

    private String password;

}
