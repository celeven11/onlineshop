package com.example.service.Impl;

import com.example.mapper.AddressMapper;
import com.example.mapper.CategoryMapper;
import com.example.pojo.Address;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;



}
