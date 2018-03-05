package com.citibanamex.mafcs.commoditycatalog.formatter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.citibanamex.mafcs.commoditycatalog.model.MetalSummaryDto;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.Coin;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalSummaryResponse;

@Component
public class MetalSummaryFormatter {

		
	
	public MetalSummaryResponse metalResponseFormatter(List<MetalSummaryDto> ltMetalDetailDto){
		MetalSummaryResponse metalSummaryResponse = new MetalSummaryResponse();
		for (MetalSummaryDto metalDetailDto: ltMetalDetailDto) {
			Coin coin = new Coin();
			
			coin.setmetalCode(metalDetailDto.getMetalKey());
			coin.setcoinDenomination(metalDetailDto.getCoin());
			coin.setmetalValue(metalDetailDto.getPesosMetal());
			coin.setsalePrice(metalDetailDto.getSalePrice());
			coin.setbuyPrice(metalDetailDto.getBuyPrice());
			
			metalSummaryResponse.getCoin().add(coin);
		}
		
				
		return metalSummaryResponse;
	}
}
