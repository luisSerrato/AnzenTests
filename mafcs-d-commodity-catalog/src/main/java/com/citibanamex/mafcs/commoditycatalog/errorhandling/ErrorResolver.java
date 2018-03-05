package com.citibanamex.mafcs.commoditycatalog.errorhandling;

import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.CcC080AddressClientException;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.DatabaseMsClientException;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.MicroserviceClientException;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.ValidationException;
import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.YearFormatException;

import feign.RetryableException;


@ControllerAdvice
public class ErrorResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorResolver.class);
	
	public static final String BADREQUESTEXCEPTION_ERROR_CODE = "PME-400";
	public static final String CCC080ADDRESSCLIENTEXCEPTION_ERROR_CODE = "PME-523";
	public static final String MICROSERVICE_CLIENT_ERROR_CODE = "PME-530";
	public static final String EXCEPTION_ERROR_CODE = "PME-500";
	public static final String DATABASEMSCLIENTEXCEPTION_ERROR_CODE = "PME-550";
	public static final String HTTPMESSAGENOTREADABLEEXCEPTION_ERROR_CODE = "PME-410";
	public static final String METHODARGUMENTNOTVALIDEXCEPTION_ERROR_CODE = "PME-411";
	public static final String SQLEXCEPTION_ERROR_CODE = "PME-520";
	public static final String VALIDATIONEXCEPTION_ERROR_CODE = "PME-470";
	

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse resolveException(HttpServletRequest req, Exception e) {
		LOGGER.error(e.getMessage(), e);
		LOGGER.info(e.getClass().getName());

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.FATAL.name());
		errorResponse.setCode(EXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());

		return errorResponse;
	}
	
	@ExceptionHandler(DatabaseMsClientException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse resolveDatabaseMsClientException(HttpServletRequest req, Exception e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.ERROR.name());
		errorResponse.setCode(DATABASEMSCLIENTEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());

		return errorResponse;
	}
	
	@ExceptionHandler(CcC080AddressClientException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse resolveCcC080AddressClientException(HttpServletRequest req, Exception e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.ERROR.name());
		errorResponse.setCode(CCC080ADDRESSCLIENTEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());

		return errorResponse;
	}
	
	@ExceptionHandler(RetryableException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse resolveSocketTimeoutException(HttpServletRequest req, RetryableException e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.ERROR.name());
		errorResponse.setCode(CCC080ADDRESSCLIENTEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());

		return errorResponse;
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse resolveMethodArgumentTypeMismatchException(HttpServletRequest req, Exception e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.ERROR.name());
		errorResponse.setCode(BADREQUESTEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getCause().getMessage());
		errorResponse.setLocation("[year]");

		return errorResponse;
	}
	
	@ExceptionHandler(YearFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse resolveYearFormatException(HttpServletRequest req, Exception e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.ERROR.name());
		errorResponse.setCode(BADREQUESTEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());
		errorResponse.setLocation("[year]");

		return errorResponse;
	}
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse resolveValidationException(HttpServletRequest req, Exception e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.ERROR.name());
		errorResponse.setCode(VALIDATIONEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());

		return errorResponse;
	}
	
	@ExceptionHandler(MicroserviceClientException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse resolveBadRequestFeignException(HttpServletRequest req, MicroserviceClientException e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.ERROR.name());
		errorResponse.setCode(ErrorResolver.MICROSERVICE_CLIENT_ERROR_CODE);
		errorResponse.setDetails(ErrorCatalog.GENERIC_ERROR_DESC);

		return errorResponse;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse resolveHttpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.INVALID.name());
		errorResponse.setCode(HTTPMESSAGENOTREADABLEEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());

		return errorResponse;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse resolveMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.INVALID.name());
		errorResponse.setCode(HTTPMESSAGENOTREADABLEEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());
		
		List<String> fields = new ArrayList<>();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			fields.add(fieldError.getField());
		}
		
		errorResponse.setLocation(fields.toString());
		
		return errorResponse;
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ErrorResponse resolveUncategorizedSqlException(HttpServletRequest request, SQLException e) {
		LOGGER.error(e.getMessage(), e);

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setType(ErrorType.INVALID.name());
		errorResponse.setCode(SQLEXCEPTION_ERROR_CODE);
		errorResponse.setDetails(e.getMessage());
		errorResponse.setLocation("sql");

		return errorResponse;
	}

}