package com.demo.myapp.services.impl;

import com.demo.myapp.entity.Bus;
import com.demo.myapp.entity.Route;
import com.demo.myapp.exception.ResourceNotFoundException;
import com.demo.myapp.payloads.BusDto;
import com.demo.myapp.payloads.CodeDto;
import com.demo.myapp.payloads.RouteDto;
import com.demo.myapp.repository.BusRepo;
import com.demo.myapp.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private com.demo.myapp.repository.RouteRepo RouteRepo;

    @Autowired
    private BusServiceImpl busserviceimpl;

    @Autowired
    private BusRepo busrepo;



    @Override
    public RouteDto createroute(RouteDto RouteDto) {
        Route Route = this.dtoToroute(RouteDto);
        Route savedroute = this.RouteRepo.save(Route);
        return this.routeToDto(savedroute);
    }

    @Override
    public List<BusDto> getBySrcAndDest(String src, String dest) {

        List<Bus> buses = busrepo.findBySrcAndDest(src, dest);

        List<BusDto> result = new ArrayList<>();
//        for( Object obj: buses.get(0)){
//            busdto busdto = new busdto();
//            busdto.setBusName(obj);
//        }
        for( Bus bus: buses ){
            BusDto busdto = busserviceimpl.busToDto(bus);
            result.add(busdto);
        }
        return result;
    }

    @Override
    public RouteDto updateroute(RouteDto RouteDto, Integer route_no) {
        Route Route = this.RouteRepo.findById(route_no).orElseThrow(() -> new ResourceNotFoundException("route", "route_no", route_no));
        Route.setDistance(RouteDto.getDistance());
        Route.setLoc_name(RouteDto.getLoc_name());
        Route.setRank_no(RouteDto.getRank_no());
        Route.setDuration(RouteDto.getDuration());
        Route.setRoute_id(RouteDto.getRoute_id());
       // Route.setArrival_time(RouteDto.getArrival_time());
        com.demo.myapp.entity.Route updatedroute = this.RouteRepo.save(Route);
        com.demo.myapp.payloads.RouteDto routeDto1 = this.routeToDto(updatedroute);
        return routeDto1;
    }

    @Override
    public RouteDto getrouteById(Integer route_no) {
        Route Route = this.RouteRepo.findById(route_no).orElseThrow(() -> new ResourceNotFoundException("route", "route_no", route_no));
        return this.routeToDto(Route);
    }

    @Override
    public List<RouteDto> getAllroute() {
        List<Route> Route = this.RouteRepo.findAll();
        List<RouteDto> routeDtos = Route.stream().map(route -> this.routeToDto(route)).collect(Collectors.toList());
        return routeDtos;
    }

    @Override
    public void deleteroute(Integer route_no) {
        Route Route = this.RouteRepo.findById(route_no).orElseThrow(() -> new ResourceNotFoundException("route", "route_no", route_no));
        this.RouteRepo.delete(Route);
    }
    @Override
    public List<CodeDto> getCodeById(){

        return this.RouteRepo.findCodeById();

}

    public Route dtoToroute(RouteDto RouteDto) {
        Route Route = new Route();
        Route.setDistance(RouteDto.getDistance());
        Route.setLoc_name(RouteDto.getLoc_name());
        Route.setRank_no(RouteDto.getRank_no());
        Route.setRoute_id(RouteDto.getRoute_id());
        Route.setRoute_primary_id(RouteDto.getRoute_primary_id());
        Route.setDuration(RouteDto.getDuration());
     //   Route.setArrival_time(RouteDto.getArrival_time());
       // Route.setDeparture_time(RouteDto.getDeparture_time());
        return Route;
    }

    public RouteDto routeToDto(Route Route) {
        RouteDto RouteDto = new RouteDto();
        RouteDto.setDistance(Route.getDistance());
        RouteDto.setLoc_name(Route.getLoc_name());
        RouteDto.setRank_no(Route.getRank_no());
        RouteDto.setRoute_id(Route.getRoute_id());
        RouteDto.setDuration(Route.getDuration());
        RouteDto.setRoute_primary_id(Route.getRoute_primary_id());
      //  RouteDto.setArrival_time(Route.getArrival_time());
        //RouteDto.setDeparture_time(Route.getDeparture_time());

        return RouteDto;
    }
}