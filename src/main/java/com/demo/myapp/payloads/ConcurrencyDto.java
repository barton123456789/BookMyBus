package com.demo.myapp.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@NoArgsConstructor
@Getter
@Setter
public class ConcurrencyDto {
    private int concurrency_id;
    private int user_id;
    private int bus_id;
    private int seat_no;
    private String status;
    private Time time;
}
