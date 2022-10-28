package com.demo.myapp.payloads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookingDto {
    private int booking_id;
    private int bus_id;
    //private userdto user;
    private int count;
    private int total_fare;
    private Date booking_date;
    private Date journey_date;
    private String booking_status;
    private String trip_source;
    private String trip_destination;
    private int user_id;
}