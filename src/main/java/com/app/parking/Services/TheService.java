package com.app.parking.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.parking.Entities.The;
import com.app.parking.Entities.Xe;
import com.app.parking.Repositories.TheRepository;
import com.app.parking.Repositories.XeRepository;


@Service
public class TheService {
	
	@Autowired
	private TheRepository theRepository;
	
	public void save_The(The the){
		
		theRepository.save(the);
    }
	
	
}