package com.demo.myapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Concurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int concurrency_id;
    private int user_id;
    private int bus_id;
    private int seat_no;
    private String status;
    private Time time;
}
