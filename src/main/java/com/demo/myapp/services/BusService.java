package com.demo.myapp.services;
import com.demo.myapp.payloads.BusDto;
import java.util.List;
public interface BusService {
    BusDto createbus(BusDto bus);
    BusDto updatebus(BusDto bus, Integer busId );
    BusDto getbusById(Integer busId);
    List<BusDto> getAllbus();
    void deletebus(Integer busId);

    List<BusDto> getbuschoice(String source, String destination, String days);

}