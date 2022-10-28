package com.demo.myapp.services.impl;

import com.demo.myapp.entity.Concurrency;
import com.demo.myapp.exception.ResourceNotFoundException;
import com.demo.myapp.payloads.ConcurrencyDto;
import com.demo.myapp.repository.ConcurrencyRepo;
import com.demo.myapp.services.ConcurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcurrencyServiceImpl implements ConcurrencyService {

    @Autowired
    private ConcurrencyRepo concurrencyrepo;

//      @Scheduled(fixedRate = 1000*60)
//      public void deleteEntries() {
//        System.out.println("deleteEntries() Triggered");
//        // get current time
////        // get concurrency ids which are to be deleted ( input -- current time )
////        // select c.concurrency_id from concurrency c where timediff(c.schedule, '16:22:22') > '00:01:00';
////
////        // delete from table where ids in (above ids list)
//   }

    @Override
    public ConcurrencyDto createConcurrency(ConcurrencyDto concurrencydto) {
        Concurrency concurrency=this.dtoToConcurrency(concurrencydto);
        com.demo.myapp.entity.Concurrency savedconcurrency=this.concurrencyrepo.save(concurrency);
        return this.concurrencyToDto(savedconcurrency);
    }
    @Override
    public ConcurrencyDto getConcurrencyById(Integer concurrency_id) {
        Concurrency concurrency=this.concurrencyrepo.findById(concurrency_id).orElseThrow(() -> new ResourceNotFoundException("concurrency","concurrency_id",concurrency_id));
        return this.concurrencyToDto(concurrency);
    }

    @Override
    public List<ConcurrencyDto> getAllConcurrency() {
        List<Concurrency> concurrency= this.concurrencyrepo.findAll();
        List<ConcurrencyDto> concurrencyDtos=concurrency.stream().map(Concurrency ->this.concurrencyToDto(Concurrency)).collect(Collectors.toList());
        return concurrencyDtos;
    }
    @Override
    public void deleteConcurrency(Integer concurrency_id) {
        Concurrency concurrency=this.concurrencyrepo.findById(concurrency_id).orElseThrow(()-> new ResourceNotFoundException("concurrency","concurrency_id",concurrency_id));
        this.concurrencyrepo.delete(concurrency);
    }

    public Concurrency dtoToConcurrency(ConcurrencyDto concurrencyDto){
        Concurrency concurrency= new Concurrency();
        concurrency.setBus_id(concurrencyDto.getBus_id());
        concurrency.setUser_id(concurrencyDto.getUser_id());
        concurrency.setConcurrency_id(concurrencyDto.getConcurrency_id());
        concurrency.setSeat_no(concurrencyDto.getSeat_no());
        concurrency.setStatus(concurrencyDto.getStatus());
        concurrency.setTime(concurrencyDto.getTime());

        return  concurrency;
    }
    public ConcurrencyDto concurrencyToDto(Concurrency concurrency){
        ConcurrencyDto concurrencyDto= new ConcurrencyDto();
        concurrencyDto.setBus_id(concurrency.getBus_id());
        concurrencyDto.setUser_id(concurrency.getUser_id());
        concurrencyDto.setConcurrency_id(concurrency.getConcurrency_id());
        concurrencyDto.setSeat_no(concurrency.getSeat_no());
        concurrencyDto.setStatus(concurrency.getStatus());
        concurrencyDto.setTime(concurrency.getTime());

        return  concurrencyDto;
    }


}
