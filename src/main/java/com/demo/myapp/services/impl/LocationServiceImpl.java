package com.demo.myapp.services.impl;
import com.demo.myapp.entity.Location;
import com.demo.myapp.exception.ResourceNotFoundException;
import com.demo.myapp.payloads.LocationDto;
import com.demo.myapp.repository.BusRepo;
import com.demo.myapp.repository.LocationrRepo;
import com.demo.myapp.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationrRepo LocationRepo;
    @Autowired
    private BusRepo busrepo;
//    @Autowired
//    private locationserviceimpl locationserviceimpl;

    @Override
    public LocationDto createlocation(LocationDto LocationDto) {
        Location Location=this.dtoTolocation(LocationDto);
        com.demo.myapp.entity.Location savedlocation=this.LocationRepo.save(Location);
        return this.locationToDto(savedlocation);
    }

    @Override
    public LocationDto updatelocation(LocationDto LocationDto, Integer loc_id) {
        Location Location=this.LocationRepo.findById(loc_id).orElseThrow(()-> new ResourceNotFoundException("location","demo",loc_id));
        Location.setLocName(LocationDto.getLocName());
        Location.setLatitude(LocationDto.getLatitude());
        Location.setLongitude(LocationDto.getLatitude());
        com.demo.myapp.entity.Location updatedlocation=this.LocationRepo.save(Location);
        com.demo.myapp.payloads.LocationDto LocationDto1=this.locationToDto(updatedlocation);
        return LocationDto1;
    }

    @Override
    public LocationDto getlocationById(Integer loc_id) {
        Location Location=this.LocationRepo.findById(loc_id).orElseThrow(() -> new ResourceNotFoundException("location","loc_id",loc_id));
        return this.locationToDto(Location);
    }

    @Override
    public List<LocationDto> getAlllocation() {
        List<Location> Location= this.LocationRepo.findAll();
        List<LocationDto> locationDtos=Location.stream().map(location ->this.locationToDto(location)).collect(Collectors.toList());
        return locationDtos;
    }

    @Override
    public void deletelocation(Integer loc_id) {
        Location Location=this.LocationRepo.findById(loc_id).orElseThrow(()-> new ResourceNotFoundException("location","loc_id",loc_id));
        this.LocationRepo.delete(Location);
    }

    @Override
    public List<LocationDto> getLocationByBus(int busId) {

        List<Location> locations = LocationRepo.findLocationByBus(busId);

        List<LocationDto> result = new ArrayList<>();

        for( Location location: locations ){
            LocationDto locationdto = locationToDto(location);
            result.add(locationdto);
        }
        return result;
    }

    public Location dtoTolocation(LocationDto LocationDto){
        Location Location= new Location();
        Location.setLoc_id(LocationDto.getLoc_id());
        Location.setLocName(LocationDto.getLocName());
        Location.setLatitude(LocationDto.getLatitude());
        Location.setLongitude(LocationDto.getLongitude());
        return  Location;
    }
    public LocationDto locationToDto(Location Location){
        LocationDto LocationDto= new LocationDto();
        LocationDto.setLoc_id(Location.getLoc_id());
        LocationDto.setLocName(Location.getLocName());
        LocationDto.setLatitude(Location.getLatitude());
        LocationDto.setLongitude(Location.getLongitude());
        return LocationDto;
    }
}