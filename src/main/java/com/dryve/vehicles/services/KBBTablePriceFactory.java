package com.dryve.vehicles.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryve.vehicles.SpringApplicationContext;

@Service
public class KBBTablePriceFactory {

	@Autowired
	private KBBTablePriceMock kbbTablePriceMock;
	
	@Autowired
	private KBBTablePriceImpl kbbTablePriceImpl;
	
	public KBBTablePrice getImpl() {
		
		if( SpringApplicationContext.isTestEnv() ) {
			return kbbTablePriceMock;
		}
		
		return kbbTablePriceImpl;
	}
	
}