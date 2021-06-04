package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther shkstart
 * @date 2021/5/22 - 23:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userState;
}
