package com.citibanamex.mafcs.commoditycatalog.formatter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.citibanamex.mafcs.commoditycatalog.model.CoinDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalDetailDto;
import com.citibanamex.mafcs.commoditycatalog.model.MetalDto;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.Coin;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.CoinMetal;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.Metal;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.metaldetail.MetalResponse;

@Component
public class MetalFormatter {



	public MetalResponse metalResponseFormatter(List<MetalDto> allMetals){
		MetalResponse metalResponse = new MetalResponse();
		
		for (MetalDto metalDto : allMetals) {
			Metal metal = new Metal();
			metal.setmetalType(metalDto.getMetal());
			metal.setCoinMetal(getCoinMetal(metalDto.getCoins()));
			
			metalResponse.getMetal().add(metal);			
		}
		
		
		return metalResponse;
	}
	
	
	private List<CoinMetal> getCoinMetal(List<CoinDto> ltCoinDto){
		List<CoinMetal> ltCoinMetal = new ArrayList<>();
		for (CoinDto coinDto : ltCoinDto) {
			CoinMetal coinMetal = new CoinMetal();
			coinMetal.setcoinCategory(coinDto.getCoinName());
			coinMetal.setCoin(getCoin(coinDto.getCoins()));
			
			ltCoinMetal.add(coinMetal);
		}
		return ltCoinMetal;
	}
	
	
	private List<Coin> getCoin(List<MetalDetailDto> ltMetalDetailDto){
		List<Coin> ltCoin = new ArrayList<>();
		for (MetalDetailDto metalDetailDto: ltMetalDetailDto) {
			Coin coin = new Coin();
			
			coin.setmetalCode(metalDetailDto.getMetalKey());
			coin.setcoinDenomination(metalDetailDto.getCoin());
			coin.setmetalValue(metalDetailDto.getPesosMetal());
			coin.setsalePrice(metalDetailDto.getSalePrice());
			coin.setbuyPrice(metalDetailDto.getBuyPrice());
			
			ltCoin.add(coin);
		}
				
		return ltCoin;
	}

}
