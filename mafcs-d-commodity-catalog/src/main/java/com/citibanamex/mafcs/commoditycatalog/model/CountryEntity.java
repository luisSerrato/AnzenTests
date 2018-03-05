package com.citibanamex.mafcs.commoditycatalog.model;

public class CountryEntity {

	private String idPais;
	private String pais;

	public CountryEntity(String idPaisValue, String paisValue) {
		idPais = idPaisValue;
		pais = paisValue;
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPaisValue) {
		idPais = idPaisValue;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String paisValue) {
		pais = paisValue;
	}

}
