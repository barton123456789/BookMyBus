package com.demo.myapp.payloads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@NoArgsConstructor
@Getter
@Setter
public class RouteDto {
    private int route_primary_id;
    private int route_id;
    private int rank_no;
    private  String loc_name;
   // private Time arrival_time;
  //  private Time departure_time;
    private  int distance;
    private int Duration;
}