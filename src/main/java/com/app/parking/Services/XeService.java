package com.app.parking.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.parking.Entities.Xe;
import com.app.parking.Repositories.XeRepository;


@Service
public class XeService {
	
	@Autowired
	private XeRepository xeRepository;
	
	public Xe save_Xe(Xe xe){
		
		return xeRepository.save(xe);
    }
	
	
}