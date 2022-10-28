package com.demo.myapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OccupancyDto {
    private  int occupancyid;
   private int booking_id;
    private int seat_no;
    private String passenger_name;
    private  int passenger_age;
    private  String passenger_gender;
}
