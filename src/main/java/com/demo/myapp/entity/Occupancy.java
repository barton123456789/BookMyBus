package com.demo.myapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="occupancy")
@NoArgsConstructor
@Getter
@Setter
public class Occupancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int occupancyid;
    private int booking_id;
    private int seat_no;
    private String passenger_name;
    private  int passenger_age;
    private  String passenger_gender;



//    @ManyToOne
//    @JoinColumn(name = "booking_id")
//    @JsonBackReference
//    private Booking books;




}
