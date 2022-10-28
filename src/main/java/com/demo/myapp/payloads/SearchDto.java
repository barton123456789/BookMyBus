package com.demo.myapp.payloads;

import lombok.Data;

import java.sql.Date;

@Data
public class SearchDto {

    private int bus_id;
    private  int capacity;
    private  String source;
    private  String destination;
    private Date date;
}
