package com.demo.myapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BusDto {
    private int bus_id;
    private String busName;
    private String type;//ac non-ac
    private int capacity;
    private String source;
    private String destination;
    private int fare;
   // private String seat_no;
    private String Passenger_name;
    private int route_id;
    private String days;

}