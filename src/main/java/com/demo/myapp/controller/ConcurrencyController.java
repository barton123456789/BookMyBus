package com.demo.myapp.controller;

import com.demo.myapp.payloads.BusDto;
import com.demo.myapp.payloads.ConcurrencyDto;
import com.demo.myapp.payloads.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("app/concurrency")
@CrossOrigin(origins="http://localhost:3000")
public class ConcurrencyController {

        @Autowired
        private com.demo.myapp.services.ConcurrencyService concurrencyservice;
        @PostMapping("/")
        public ResponseEntity<ConcurrencyDto> createConcurrency(@RequestBody ConcurrencyDto concurrencydto){
            com.demo.myapp.payloads.ConcurrencyDto createConcurrencyDto=this.concurrencyservice.createConcurrency(concurrencydto);
            return new ResponseEntity<>(createConcurrencyDto, HttpStatus.CREATED);
        }
    @DeleteMapping("/{concurrencyId}")
    public ResponseEntity<Response> deleteconcurrency(@PathVariable("concurrencyId") Integer cyid){
        this.concurrencyservice.deleteConcurrency(cyid);
        return new ResponseEntity(new Response("record deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<ConcurrencyDto>> getAllconcurrency(){
        return ResponseEntity.ok(this.concurrencyservice.getAllConcurrency());
    }
    @GetMapping("/{concurrencyId}")
    public ResponseEntity<ConcurrencyDto> getSingleconcurrency(@PathVariable Integer concurrencyId){
        return  ResponseEntity.ok(this.concurrencyservice.getConcurrencyById(concurrencyId));
    }
}
