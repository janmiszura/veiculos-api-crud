package com.dryve.vehicles.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dryve.util.HttpRequestUtil;
import com.dryve.util.HttpResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KBBTablePriceImpl implements KBBTablePrice {
	
	static Logger logger = LoggerFactory.getLogger(KBBTablePriceImpl.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public KBBPrice findById(Long id) throws Exception {
		
		//TODO mover para outro lugar mais apropriado
		String url = "https://6048bdf1fb5dcc0017968e3f.mockapi.io/api/v1/kbb/prices/"+id;
		
		HttpResponseDTO responseDTO = HttpRequestUtil.makeGet(url);
		
		Boolean ehOK = responseDTO.getHttpCode() == 200;
		
		if( ! ehOK ) {
			logger.error("[ERROR] url: "+url+", "+responseDTO);
			return null;
		}
		
		String json = responseDTO.getResponse();
		
		KBBPrice kbbPrice = mapper.readValue(json, KBBPrice.class);
		
		logger.info("[INFO] url: "+url+", "+responseDTO);
		
		return kbbPrice;
	}

}
