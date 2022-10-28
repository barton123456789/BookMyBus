package com.demo.myapp.services;

import com.demo.myapp.payloads.OccupancyDto;

import java.util.List;

public interface OccupancyService {
    OccupancyDto createoccupancy(OccupancyDto occupancy);
    OccupancyDto updateoccupancy(OccupancyDto occupancy, Integer occupancyid );
    OccupancyDto getoccupancyById(Integer occupancyid);


    List<OccupancyDto> getAllOccupancy();

    void deleteoccupancy(Integer occupancyid);

    List<OccupancyDto> getOccByBookId(int bookId);

}
