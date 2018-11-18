package com.selfcabinet.service;

import com.selfcabinet.mapper.CupboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupboardService {
    private final CupboardMapper cupboardMapper;


    @Autowired
    public CupboardService(CupboardMapper cupboardMapper){
        this.cupboardMapper=cupboardMapper;
    }
}
