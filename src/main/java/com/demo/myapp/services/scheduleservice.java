package com.demo.myapp.services;


import com.demo.myapp.payloads.ScheduleDto;

import java.util.List;

public interface scheduleservice {

    ScheduleDto createschedule(ScheduleDto scheduleDto);
    ScheduleDto updateschedule(ScheduleDto ScheduleDto, Integer scheduleId );
    ScheduleDto getscheduleById(Integer scheduleId );



    List<ScheduleDto> getAllschedule();
    void deleteschedule(Integer scheduleId);
}