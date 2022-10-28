package com.demo.myapp.controller;
import com.demo.myapp.payloads.Response;
import com.demo.myapp.payloads.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/loc")
public class LocationController {
    @Autowired
    private com.demo.myapp.services.LocationService LocationService;
    @PostMapping("/")
    public ResponseEntity<LocationDto> createlocation(@RequestBody LocationDto LocationDto){
        com.demo.myapp.payloads.LocationDto createlocationDto=this.LocationService.createlocation(LocationDto);
        return new ResponseEntity<>(createlocationDto, HttpStatus.CREATED);
    }
    @PutMapping("/{loc_id}")
    public ResponseEntity<LocationDto> updatelocation(@RequestBody LocationDto LocationDto, @PathVariable Integer loc_id){
        com.demo.myapp.payloads.LocationDto updatelocation=this.LocationService.updatelocation(LocationDto,loc_id);
        return ResponseEntity.ok(updatelocation);
    }
    @DeleteMapping("/{loc_id}")
    public ResponseEntity<Response> deletelocation(@PathVariable("loc_id") Integer lid){
        this.LocationService.deletelocation(lid);
        return new ResponseEntity(new Response("user deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<LocationDto>> getAlllocation(){
        return ResponseEntity.ok(this.LocationService.getAlllocation());
    }
//    @GetMapping("/{loc_id}")
//    public ResponseEntity<locationdto> getSinglelocation(@PathVariable Integer loc_id){
//        return  ResponseEntity.ok(this.LocationService.getlocationById(loc_id));
//    }
    @GetMapping("/getLocationByBus")
    public List<LocationDto> getBySrcAndDest(@RequestParam(name = "busId") int busId) {
        return LocationService.getLocationByBus(busId);
    }
}
