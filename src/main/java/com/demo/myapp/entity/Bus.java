package com.demo.myapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bus_details")
@NoArgsConstructor
@Getter
@Setter
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int bus_id;
    private String busName;
    private String type;
    private int capacity;
    private String source;
    private String destination;
    private int fare;
    private String days;
    private int route_id;


}
