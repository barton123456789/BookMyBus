package com.demo.myapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="bus_schedule")
@NoArgsConstructor
@Getter
@Setter
public class Bus_schedule {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int scheduleId;
    private int bus_id;
    private int route_id;
}
