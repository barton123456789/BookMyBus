package com.demo.myapp.controller;

import com.demo.myapp.payloads.Response;
import com.demo.myapp.payloads.OccupancyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/occu")
public class OccupancyController {

    @Autowired
    private com.demo.myapp.services.OccupancyService OccupancyService;
    @PostMapping("/")
    public ResponseEntity<OccupancyDto> createoccupancy(@RequestBody OccupancyDto OccupancyDto){
        com.demo.myapp.payloads.OccupancyDto createoccupancyDto=this.OccupancyService.createoccupancy(OccupancyDto);
        return new ResponseEntity<>(createoccupancyDto, HttpStatus.CREATED);
    }
    @PutMapping("/{occupancyid}")
    public ResponseEntity<OccupancyDto> updateoccupancy(@RequestBody OccupancyDto OccupancyDto, @PathVariable Integer occupancyid){
        com.demo.myapp.payloads.OccupancyDto updateoccupancy=this.OccupancyService.updateoccupancy(OccupancyDto,occupancyid);
        return ResponseEntity.ok(updateoccupancy);
    }
    @DeleteMapping("/{occupancyid}")
    public ResponseEntity<Response> deleteoccupancy(@PathVariable("busId") Integer oid){
        this.OccupancyService.deleteoccupancy(oid);
        return new ResponseEntity(new Response("user deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<OccupancyDto>> getAllOccupancy(){
        return ResponseEntity.ok(this.OccupancyService.getAllOccupancy());
    }
    @GetMapping("/{occupancyid}")
    public ResponseEntity<OccupancyDto> getSingleoccupancy(@PathVariable Integer occupancyid){
        return  ResponseEntity.ok(this.OccupancyService.getoccupancyById(occupancyid));
    }

    @GetMapping("/getOccByBook")
    public List<OccupancyDto> getOccByBookId(@RequestParam(name = "bookId") int bookId) {
        return OccupancyService.getOccByBookId(bookId);
    }


}
