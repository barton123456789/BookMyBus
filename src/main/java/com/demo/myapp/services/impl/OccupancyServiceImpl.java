package com.demo.myapp.services.impl;

import com.demo.myapp.entity.Occupancy;
import com.demo.myapp.exception.ResourceNotFoundException;
import com.demo.myapp.payloads.OccupancyDto;
import com.demo.myapp.services.OccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OccupancyServiceImpl implements OccupancyService {

    @Autowired
    private com.demo.myapp.repository.OccupancyRepo OccupancyRepo;

    @Override
    public OccupancyDto createoccupancy(OccupancyDto OccupancyDto) {
        Occupancy occupancy=this.dtoTooccupancy(OccupancyDto);
        Occupancy savedoccupancy=this.OccupancyRepo.save(occupancy);
        return this.occupancyToDto(savedoccupancy);
    }

    @Override
    public OccupancyDto updateoccupancy(OccupancyDto OccupancyDto, Integer occupancyid) {
        Occupancy occupancy=this.OccupancyRepo.findById(occupancyid).orElseThrow(()-> new ResourceNotFoundException("occupancy","occupancyid",occupancyid));
        occupancy.setOccupancyid(OccupancyDto.getOccupancyid());
       occupancy.setBooking_id(OccupancyDto.getBooking_id());
        occupancy.setSeat_no(OccupancyDto.getSeat_no());
        occupancy.setPassenger_name(OccupancyDto.getPassenger_name());
        occupancy.setPassenger_age(OccupancyDto.getPassenger_age());
        occupancy.setPassenger_gender(OccupancyDto.getPassenger_gender());


        //Booking.setUser_id(BookingDto.getUser_id());

        Occupancy updatedoccupancy=this.OccupancyRepo.save(occupancy);
        com.demo.myapp.payloads.OccupancyDto occupancyDto1=this.occupancyToDto(updatedoccupancy);
        return occupancyDto1;
    }

    @Override
    public OccupancyDto getoccupancyById(Integer occupancyid) {
        Occupancy occupancy=this.OccupancyRepo.findById(occupancyid).orElseThrow(() -> new ResourceNotFoundException("occupancy","occupancyid",occupancyid));
        return this.occupancyToDto(occupancy);
    }

    @Override
    public List<OccupancyDto> getOccByBookId(int bookingId) {

        List<Occupancy> occupancies = OccupancyRepo.findOccByBookId(bookingId);

        List<OccupancyDto> result = new ArrayList<>();

        for( Occupancy occupancy: occupancies ){
            OccupancyDto occupancydto = occupancyToDto(occupancy);
            result.add(occupancydto);
        }
        return result;
    }

@Override
    public List<OccupancyDto> getAllOccupancy() {
        List<Occupancy> Occupancy= this.OccupancyRepo.findAll();
        List<OccupancyDto> occupancyDtos;
        occupancyDtos=Occupancy.stream().map(occupancy ->this.occupancyToDto(occupancy)).collect(Collectors.toList());
        return occupancyDtos;
    }

    @Override
    public void deleteoccupancy(Integer occupancyid) {
        Occupancy occupancy =this.OccupancyRepo.findById(occupancyid).orElseThrow(()-> new ResourceNotFoundException("occupancy","occupancyid",occupancyid));
        this.OccupancyRepo.delete(occupancy);
    }
    public Occupancy dtoTooccupancy(OccupancyDto OccupancyDto){
        Occupancy occupancy =new Occupancy();
        occupancy.setOccupancyid(OccupancyDto.getOccupancyid());
       occupancy.setBooking_id(OccupancyDto.getBooking_id());
        occupancy.setSeat_no(OccupancyDto.getSeat_no());
        occupancy.setPassenger_name(OccupancyDto.getPassenger_name());
        occupancy.setPassenger_age(OccupancyDto.getPassenger_age());
        occupancy.setPassenger_gender(OccupancyDto.getPassenger_gender());

        return  occupancy;
    }

    public OccupancyDto occupancyToDto(Occupancy Occupancy){
        OccupancyDto OccupancyDto= new OccupancyDto();
        OccupancyDto.setOccupancyid(Occupancy.getOccupancyid());
      OccupancyDto.setBooking_id(Occupancy.getBooking_id());
        OccupancyDto.setSeat_no(Occupancy.getSeat_no());
        OccupancyDto.setPassenger_name(Occupancy.getPassenger_name());
        OccupancyDto.setPassenger_age(Occupancy.getPassenger_age());
        OccupancyDto.setPassenger_gender(Occupancy.getPassenger_gender());
        //BookingsDto.setUser(userservice.getUserById(Bookings.getUser_id()));

        return OccupancyDto;
    }
}
