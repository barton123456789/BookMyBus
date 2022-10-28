package com.demo.myapp.payloads;


import com.demo.myapp.entity.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {


    private int user_id;

    @Size(min = 8, max = 10,message = "Password must have atleast 8 charcters")
    private String password;

    @Size(min = 4,message = "Name must be minimum of 3 chracters ")
    private String full_Name;
    @Email(message = "Email is not Valid")
    private String email;
    private String role;
    private String gender;
    private int age;

    private List<Booking> bookings= new ArrayList<>();
}
