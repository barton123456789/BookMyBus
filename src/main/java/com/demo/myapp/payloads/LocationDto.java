package com.demo.myapp.payloads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class LocationDto {
    private int loc_id;
    private  String locName;
    private  String latitude;
    private  String longitude;
}