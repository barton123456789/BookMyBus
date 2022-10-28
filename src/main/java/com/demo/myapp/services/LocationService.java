package com.demo.myapp.services;
import com.demo.myapp.payloads.LocationDto;
import java.util.List;
public interface LocationService {
    LocationDto createlocation(LocationDto location);
    LocationDto updatelocation(LocationDto location, Integer loc_id );
    LocationDto getlocationById(Integer loc_id);
    List<LocationDto> getAlllocation();
    void deletelocation(Integer loc_id);

    List<LocationDto> getLocationByBus(int busId);
}