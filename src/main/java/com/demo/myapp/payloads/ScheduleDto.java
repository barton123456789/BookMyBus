package com.demo.myapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@NoArgsConstructor
@Getter
@Setter

public class ScheduleDto {
    private  int scheduleId;

    private int bus_id;
    private int route_id;
    private Time arrival_time;

}
