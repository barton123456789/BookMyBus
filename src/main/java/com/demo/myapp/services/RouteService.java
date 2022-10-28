package com.demo.myapp.services;
import com.demo.myapp.payloads.BusDto;
import com.demo.myapp.payloads.CodeDto;
import com.demo.myapp.payloads.RouteDto;
import java.util.List;
public interface RouteService {
    RouteDto createroute(RouteDto route);
    RouteDto updateroute(RouteDto route, Integer route_id );
    RouteDto getrouteById(Integer route_id);
    List<RouteDto> getAllroute();
    void deleteroute(Integer route_id);
  //  List<Integer> findBySrcAndDest(String src,String dest);

    List<BusDto> getBySrcAndDest(String src, String dest);
    List<CodeDto> getCodeById();
}

