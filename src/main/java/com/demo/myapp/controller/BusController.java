package com.demo.myapp.controller;
import com.demo.myapp.payloads.Response;
import com.demo.myapp.payloads.BusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("app/bus")
@CrossOrigin(origins="http://localhost:3000")
public class BusController {
    @Autowired
    private com.demo.myapp.services.BusService BusService;
    @PostMapping("/")
    public ResponseEntity<BusDto> createbus(@RequestBody BusDto BusDto){
        com.demo.myapp.payloads.BusDto createbusDto=this.BusService.createbus(BusDto);
        return new ResponseEntity<>(createbusDto, HttpStatus.CREATED);
    }
    @PutMapping("/{busId}")
    public ResponseEntity<BusDto> updatebus(@RequestBody BusDto BusDto, @PathVariable Integer busId){
        com.demo.myapp.payloads.BusDto updateBus=this.BusService.updatebus(BusDto,busId);
        return ResponseEntity.ok(updateBus);
    }
    @DeleteMapping("/{busId}")
    public ResponseEntity<Response> deletebus(@PathVariable("busId") Integer bsid){
        this.BusService.deletebus(bsid);
        return new ResponseEntity(new Response("user deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<BusDto>> getAllbus(){
        return ResponseEntity.ok(this.BusService.getAllbus());
    }
    @GetMapping("/{busId}")
    public ResponseEntity<BusDto> getSinglebus(@PathVariable Integer busId){
        return  ResponseEntity.ok(this.BusService.getbusById(busId));
    }


}