package com.demo.myapp.controller;

import com.demo.myapp.payloads.Response;
import com.demo.myapp.payloads.ScheduleDto;
import com.demo.myapp.services.scheduleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private scheduleservice ScheduleService;
    @PostMapping("/")
    public ResponseEntity<ScheduleDto> createschedule(@RequestBody ScheduleDto ScheduleDto){
        com.demo.myapp.payloads.ScheduleDto createscheduleDto=this.ScheduleService.createschedule(ScheduleDto);
        return new ResponseEntity<>(createscheduleDto, HttpStatus.CREATED);
    }
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDto> updateschedule(@RequestBody ScheduleDto scheduleDto, @PathVariable Integer scheduleId){
        ScheduleDto updatelocation=this.ScheduleService.updateschedule(scheduleDto,scheduleId);
        return ResponseEntity.ok(updatelocation);
    }
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Response> deleteschedule(@PathVariable("scheduleId") Integer sid){
        this.ScheduleService.deleteschedule(sid);
        return new ResponseEntity(new Response("user deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<ScheduleDto>> getAlllocation(){
        return ResponseEntity.ok(this.ScheduleService.getAllschedule());
    }

}


