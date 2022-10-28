package com.demo.myapp.services.impl;
import com.demo.myapp.entity.Bus;
import com.demo.myapp.exception.ResourceNotFoundException;
import com.demo.myapp.payloads.BusDto;
import com.demo.myapp.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private com.demo.myapp.repository.BusRepo BusRepo;
    @Override
    public BusDto createbus(BusDto BusDto) {
        Bus Bus=this.dtoTobus(BusDto);
        com.demo.myapp.entity.Bus savedbus=this.BusRepo.save(Bus);
        return this.busToDto(savedbus);
    }

    @Override
    public BusDto updatebus(BusDto BusDto, Integer busId) {
        Bus Bus=this.BusRepo.findById(busId).orElseThrow(()-> new ResourceNotFoundException("bus","bus_id",busId));
        Bus.setBus_id(BusDto.getBus_id());
        Bus.setFare(BusDto.getFare());
        Bus.setCapacity(BusDto.getCapacity());
        Bus.setSource(BusDto.getSource());
        Bus.setType(BusDto.getType());
        Bus.setDestination(BusDto.getDestination());
        Bus.setDays(BusDto.getDays());
        Bus.setBusName(BusDto.getBusName());
        com.demo.myapp.entity.Bus updatedbus=this.BusRepo.save(Bus);
        com.demo.myapp.payloads.BusDto busDto1=this.busToDto(updatedbus);
        return busDto1;
    }

    @Override
    public BusDto getbusById(Integer bus_id) {
        Bus Bus=this.BusRepo.findById(bus_id).orElseThrow(() -> new ResourceNotFoundException("bus","bus_id",bus_id));
        return this.busToDto(Bus);
    }

    @Override
    public List<BusDto> getAllbus() {
        List<Bus> Bus= this.BusRepo.findAll();
        List<BusDto> busDtos=Bus.stream().map(bus ->this.busToDto(bus)).collect(Collectors.toList());
        return busDtos;
    }


    @Override
    public List<BusDto> getbuschoice(String a, String b, String c){
        List<Bus> Bus=this.BusRepo.findAll();
        List<BusDto> busDtos=Bus.stream().map(bus ->this.busToDto(bus)).collect(Collectors.toList());
        return busDtos;
    }
    @Override
    public void deletebus(Integer bus_id) {
        Bus Bus=this.BusRepo.findById(bus_id).orElseThrow(()-> new ResourceNotFoundException("bus","bus_id",bus_id));
        this.BusRepo.delete(Bus);
    }
    public Bus dtoTobus(BusDto BusDto){
        Bus Bus= new Bus();
        Bus.setBus_id(BusDto.getBus_id());
        Bus.setFare(BusDto.getFare());
        Bus.setCapacity(BusDto.getCapacity());
        Bus.setSource(BusDto.getSource());
        Bus.setType(BusDto.getType());
        Bus.setDestination(BusDto.getDestination());
        Bus.setDays(BusDto.getDays());
        Bus.setBusName(BusDto.getBusName());
        Bus.setRoute_id(BusDto.getRoute_id());
       // Bus.setRoute_code(BusDto.getRoute_code());
        return  Bus;
    }
    public BusDto busToDto(Bus Bus){
        BusDto BusDto= new BusDto();
        BusDto.setBus_id(Bus.getBus_id());
        BusDto.setFare(Bus.getFare());
        BusDto.setCapacity(Bus.getCapacity());
        BusDto.setSource(Bus.getSource());
        BusDto.setType(Bus.getType());
        BusDto.setDestination(Bus.getDestination());
        BusDto.setDays(Bus.getDays());
        BusDto.setBusName(Bus.getBusName());
        BusDto.setRoute_id(Bus.getRoute_id());
       // BusDto.setRoute_code(Bus.getRoute_code());

        return BusDto;
    }
}