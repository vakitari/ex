package com.example.demo.Response;

import com.example.demo.Entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends BaseResponse {
    public UserResponse(Iterable<User> data)  {
        super(true, "Пользователи");
        this.data = data;
    }

    private Iterable<User> data;
}
