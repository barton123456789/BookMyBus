package com.demo.myapp.services;

import com.demo.myapp.payloads.BusDto;
import com.demo.myapp.payloads.ConcurrencyDto;

import java.util.List;

public interface ConcurrencyService {
    ConcurrencyDto createConcurrency(ConcurrencyDto concurrency);
    //ConcurrencyDto updateConcurrency(ConcurrencyDto concurrency, Integer concurrencyId );
    ConcurrencyDto getConcurrencyById(Integer concurrencyId);
    List<ConcurrencyDto> getAllConcurrency();
    void deleteConcurrency(Integer concurrencyId);
}
