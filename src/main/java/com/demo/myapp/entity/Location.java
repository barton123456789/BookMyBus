package com.demo.myapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="location_details")
@NoArgsConstructor
@Getter
@Setter
public class Location {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loc_id;
    private  String locName;
    private  String latitude;
    private  String longitude;




}
