package com.demo.myapp.services.impl;

import com.demo.myapp.entity.Bus_schedule;
import com.demo.myapp.exception.ResourceNotFoundException;
import com.demo.myapp.payloads.ScheduleDto;
import com.demo.myapp.repository.Schedulerepo;
import com.demo.myapp.services.scheduleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class scheduleserviceimpl implements scheduleservice {

    @Autowired
    private Schedulerepo ScheduleRepo;
    @Override
    public ScheduleDto createschedule(ScheduleDto scheduleDto) {
        Bus_schedule Schedule=this.dtoToschedule(scheduleDto);
        Bus_schedule savedschedule=this.ScheduleRepo.save(Schedule);
        return this.scheduleToDto(savedschedule);
    }



    @Override
    public ScheduleDto updateschedule(ScheduleDto ScheduleDto, Integer scheduleId) {
        Bus_schedule Schedule=this.ScheduleRepo.findById(scheduleId).orElseThrow(()-> new ResourceNotFoundException("bus_schedule","id",scheduleId));
        Schedule.setBus_id(ScheduleDto.getBus_id());
        Schedule.setRoute_id(ScheduleDto.getRoute_id());
       // Schedule.setArrival_time(ScheduleDto.getArrival_time());
        Bus_schedule updatedschedule=this.ScheduleRepo.save(Schedule);
        com.demo.myapp.payloads.ScheduleDto LocationDto1=this.scheduleToDto(updatedschedule);
        return LocationDto1;
    }

    @Override
    public ScheduleDto getscheduleById(Integer scheduleId) {
        Bus_schedule Schedule=this.ScheduleRepo.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("bus_schedule","id",scheduleId));
        return this.scheduleToDto(Schedule);
    }

    @Override
    public List<ScheduleDto> getAllschedule() {
        List<Bus_schedule> Location= this.ScheduleRepo.findAll();
        List<ScheduleDto> locationDtos=Location.stream().map(location ->this.scheduleToDto(location)).collect(Collectors.toList());
        return locationDtos;
    }

    @Override
    public void deleteschedule(Integer scheduleId) {
        Bus_schedule Schedule=this.ScheduleRepo.findById(scheduleId).orElseThrow(()-> new ResourceNotFoundException("bus_schedule","id",scheduleId));
        this.ScheduleRepo.delete(Schedule);
    }
    public Bus_schedule dtoToschedule(ScheduleDto ScheduleDto){
        Bus_schedule Schedule= new Bus_schedule();
        Schedule.setBus_id(ScheduleDto.getBus_id());
        Schedule.setRoute_id(ScheduleDto.getRoute_id());
       // Schedule.setArrival_time(ScheduleDto.getArrival_time());

        return Schedule;
    }
    public ScheduleDto scheduleToDto(Bus_schedule Schedule){
        ScheduleDto ScheduleDto= new ScheduleDto();

        ScheduleDto.setBus_id(Schedule.getBus_id());
        ScheduleDto.setRoute_id(Schedule.getRoute_id());
      //  ScheduleDto.setArrival_time(Schedule.getArrival_time());
        return ScheduleDto;
    }
}

