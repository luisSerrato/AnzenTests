package com.citibanamex.mafcs.commoditycatalog.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citibanamex.itmt.ccutil.commons.Result;
import com.citibanamex.itmt.ccutil.utils.Utils;
import com.citibanamex.itmt.ccutil.constants.ConstantsMessages;
import com.citibanamex.mafcs.commoditycatalog.databasemsclient.C080Client;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.CcC080AddressClientException;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.ValidationException;
import com.citibanamex.mafcs.commoditycatalog.model.CountryEntity;
import com.citibanamex.mafcs.commoditycatalog.service.LocationService;
import com.citibanamex.mafcs.commoditycatalog.viewmodel.query.SqlStatementRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service("locationService")
public class LocationServiceImpl implements LocationService{
	
	private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	private static final String CONS_QUERY_EJECUTADO = "QUERY EJECUTADO: ";
	private static final String CONS_DATOS = "datos";
	private static final String CONS_CAMPOS = "campos";
	private static final String VALIDATION_ERROR = "Response is invalid";
	
	@Autowired
	private C080Client c080Client;
	

	@Override
	public List<CountryEntity> getCountriesInfo() {
		return getCountriesClient();
	}
	
	@SuppressWarnings("unchecked")
	public List<CountryEntity> getCountriesClient() {
		String sql = "SELECT NACIONA, D_LARGA  FROM D000597";
		String logSql = CONS_QUERY_EJECUTADO + sql; 
		
		logger.info(logSql);
		Object responseC080 = getDataFromC080(sql);
		
		HashMap<String, Object> campos = new HashMap<>();
		List<Object> datosDatosGeneralesColoniaAux = new ArrayList<>();
		
		resultC080CamposMasDatos(responseC080,campos,datosDatosGeneralesColoniaAux);
		
		int numeroIdPais = (int) campos.get("NACIONA");
		int numeroPais = (int) campos.get("D_LARGA");
		
		List<CountryEntity> countriesDetails = new ArrayList<>();

		for (Object country : datosDatosGeneralesColoniaAux) {
			List<Object> countriDetails = (ArrayList<Object>) country;

			String idPaises = (String) countriDetails.get(numeroIdPais);
			String pais = (String) countriDetails.get(numeroPais);

			countriesDetails.add(new CountryEntity(idPaises, pais));
		}
		
		return countriesDetails;
	}
	
	private Object getDataFromC080(String sql) {
		SqlStatementRequest sqlRequest = new SqlStatementRequest();
		sqlRequest.setSql(sql);
		
		long t0 = System.currentTimeMillis();
		Object responseDescripcion = c080Client.getInformationC080(sqlRequest);
		String timeTotal = "Time elapsed c080Client.getInformationC080: " + (System.currentTimeMillis() - t0) + " ms";
		logger.info(timeTotal);
		logger.info("responseDescripcion: " + Utils.getJson(responseDescripcion));

		if (responseDescripcion == null) {
			throw new CcC080AddressClientException("System C080 back unavailable");
		}
		
		return responseDescripcion;
	}
	
	@SuppressWarnings("unchecked")
	private void resultC080CamposMasDatos(Object response, HashMap<String, Object> campos,List<Object> datos){
		Result result = responseMicroServicesToResult(response);
		if (!result.isSuccess()) {
			throw new ValidationException(result.getCodigo().toString());
		}

		campos.putAll((HashMap<String, Object>) result.getDatos().get(CONS_CAMPOS));
		datos.addAll((List<Object>) result.getDatos().get(CONS_DATOS));
	}

	private Result responseMicroServicesToResult(Object response) {
		HashMap<String, Object> mapResponse = entityToMap(response, false);
		if (mapResponse != null) {
			Boolean successFlag = (Boolean) mapResponse.get("success");
			if (successFlag) {
				String datosResponse = (String) mapResponse.get(CONS_DATOS);
				HashMap<String, Object> mapDatosTemp = entityToMap(datosResponse, true);
				HashMap<String, Object> mapDatos = entityToMap(mapDatosTemp, false);
				return new Result(mapDatos);
			} else {
				Integer codigo = (mapResponse.get("codigo") != null) ? (Integer) mapResponse.get("codigo")
						: ConstantsMessages.ERROR_NO_SE_ENCONTRARON_DATOS;
				String mensaje = (String) mapResponse.get("mensaje");
				return new Result(successFlag, codigo, mensaje);
			}
		} else {
			throw new ValidationException(VALIDATION_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	private HashMap<String, Object> entityToMap(Object response, boolean isString) {
		String responseString;
		if (isString) {
			responseString = (String) response;
		} else {
			responseString = Utils.getJson(response);
		}
		responseString = "{\"dat\" : " + responseString + "}";
		HashMap<String, Object> mapResponseString = null;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(responseString);
			mapResponseString = (HashMap<String, Object>) mapper.convertValue(root.at("/dat"), Map.class);
		} catch (IOException e) {
			logger.error("LOGGER " , e);
			throw new ValidationException(VALIDATION_ERROR);
		}

		return mapResponseString;
	}

	


}
