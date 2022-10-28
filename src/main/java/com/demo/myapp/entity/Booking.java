package com.demo.myapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Booking")
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int booking_id;
    private int bus_id;
    private int count;
    private int total_fare;
    private Date booking_date;
    private Date journey_date;
    private String booking_status;
    private String trip_source;
    private String trip_destination;
    private int user_id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private User users;

    @OneToMany(mappedBy = "booking_id",cascade={CascadeType.ALL})
    @JsonManagedReference
    private List<Occupancy> occus= new ArrayList<>();



}
