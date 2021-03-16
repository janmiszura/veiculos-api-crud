package com.dryve.vehicles.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class KBBTablePriceMock implements KBBTablePrice {
	
	@Override
	public KBBPrice findById(Long id) {
		
		KBBPrice kbbPrice = new KBBPrice();
		kbbPrice.setId(id.toString());
		kbbPrice.setPrice(new BigDecimal(20000));
		
		return kbbPrice;
	}

}
