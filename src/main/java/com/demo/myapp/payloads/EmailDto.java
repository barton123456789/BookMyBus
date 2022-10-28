package com.demo.myapp.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailDto {
    private BookingDto booking;
    private List<OccupancyDto> passengerDetaials;
}
