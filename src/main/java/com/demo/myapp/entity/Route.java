package com.demo.myapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;


@Entity
@Table(name = "route_details")
@NoArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int route_primary_id;

    private int route_id;
   // private Time arrival_time;
   // private Time departure_time;
    private int rank_no;
    private String loc_name;
    private int distance;
    private int Duration;
    //private int route_code;
}
