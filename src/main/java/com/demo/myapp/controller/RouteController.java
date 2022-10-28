package com.demo.myapp.controller;

import com.demo.myapp.payloads.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/route")
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {
    @Autowired
    private com.demo.myapp.services.RouteService RouteService;

    @PostMapping("/")
    public ResponseEntity<RouteDto> createroute(@RequestBody RouteDto RouteDto) {
        com.demo.myapp.payloads.RouteDto createrouteDto = this.RouteService.createroute(RouteDto);
        return new ResponseEntity<>(createrouteDto, HttpStatus.CREATED);
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<RouteDto> updateroute(@RequestBody RouteDto RouteDto, @PathVariable Integer route_no) {
        com.demo.myapp.payloads.RouteDto updateroute = this.RouteService.updateroute(RouteDto, route_no);
        return ResponseEntity.ok(updateroute);
    }

    @DeleteMapping("/{route_no}")
    public ResponseEntity<Response> deleteroute(@PathVariable("route_no") Integer rid) {
        this.RouteService.deleteroute(rid);
        return new ResponseEntity(new Response("user deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RouteDto>> getAllroute() {
        return ResponseEntity.ok(this.RouteService.getAllroute());
    }


    @GetMapping("/{route_no}")
    public ResponseEntity<RouteDto> getSingleroute(@PathVariable Integer route_no) {
        return ResponseEntity.ok(this.RouteService.getrouteById(route_no));
    }

    @GetMapping("/getBusesBySrcAndDest")
    public List<BusDto> getBySrcAndDest(@RequestParam(name = "source") String src,
                                        @RequestParam(name = "destination") String dest) {
        return RouteService.getBySrcAndDest(src, dest);
    }

    @GetMapping("/routecodes/")
    public List<CodeDto> getCodeByDto() {
        return this.RouteService.getCodeById();
    }
}
