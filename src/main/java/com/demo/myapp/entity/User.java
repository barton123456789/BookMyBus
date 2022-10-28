package com.demo.myapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int user_id;
    private String password;

    private String full_Name;

    private String email;
    private String role;
    private String gender;
    private int age;

    @OneToMany(mappedBy = "user_id",cascade={CascadeType.ALL})
    @JsonManagedReference
    private List<Booking> bookings= new ArrayList<>();


}
