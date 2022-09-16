package com.example.demo.src.Manage;

import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManageProvider {

    private final ManageDao manageDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ManageProvider(ManageDao manageDao, JwtService jwtService){
        this.manageDao = manageDao;
        this.jwtService = jwtService;
    }
}
