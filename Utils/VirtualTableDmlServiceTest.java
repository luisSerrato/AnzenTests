package com.citibanamex.opsv.virtualtableservice.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citibanamex.opsv.virtualtableservice.dao.VtDmlDao;
import com.citibanamex.opsv.virtualtableservice.model.dml.VtDmlFields;
import com.citibanamex.opsv.virtualtableservice.model.dml.VtDmlInsertHelperDto;
import com.citibanamex.opsv.virtualtableservice.model.dml.VtDmlInsertRequest;
import com.citibanamex.opsv.virtualtableservice.model.dml.VtDmlInsertResponse;
import com.citibanamex.opsv.virtualtableservice.model.dml.VtDmlRecords;
import com.citibanamex.opsv.virtualtableservice.service.helper.VtDmlServiceHelper;
import com.citibanamex.opsv.virtualtableservice.service.impl.VtDmlInsertServiceImpl;
import com.citibanamex.opsv.virtualtableservice.viewmodel.freequeryvirtualtable.VirtualTableFreeQueryResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class VirtualTableDmlServiceTest {

	@Configuration
	static class  VirtualTableDmlServiceTestConfiguration{
		
		@Bean
		public VtDmlInsertService vtDmlInsertService(){
			return new VtDmlInsertServiceImpl();
		}
		
		@Bean
		public VtDmlServiceHelper vtDmlServiceHelper(){
			return new VtDmlServiceHelper();
		}		
		
	}
	
	
	@Autowired
	private VtDmlInsertService vtDmlInsertService;
	
	
	@MockBean
	private VtDmlDao vtDmlDao;
	
	private VirtualTableFreeQueryResponse virtualTableFreeQueryResponse;
	
	private VtDmlInsertRequest vtDmlInsertRequest;
	
	private VtDmlInsertResponse vtDmlInsertResponse;
	
	
	@Before
	public void setUp(){
		
		virtualTableFreeQueryResponse = new VirtualTableFreeQueryResponse();
		virtualTableFreeQueryResponse.setResultSet(null);
		
		List<Object[]> resultSet = new ArrayList<>();
		
		Object[] objVTable = new Object[5];
		
		objVTable[0] = Long.valueOf(1);
		objVTable[1] = Long.valueOf(1);
		objVTable[2] = new String("Column 1");
		objVTable[3] = new String("TEXT");
		objVTable[4] = new String("0");
		
		resultSet.add(objVTable);
		virtualTableFreeQueryResponse.setResultSet(resultSet);

		
		
		vtDmlInsertRequest = new VtDmlInsertRequest();		
		vtDmlInsertRequest.setvTableId(new Integer(1));
		vtDmlInsertRequest.setRecords(new ArrayList<>());
		
		VtDmlRecords vtDmlRecords = new VtDmlRecords();
		vtDmlRecords.setFields(new ArrayList<>());
		
		VtDmlFields field = new VtDmlFields();
		field.setName("Column 1");
		field.setValue("Name Test");
		
		
		
		
		vtDmlRecords.getFields().add(field);
		vtDmlInsertRequest.getRecords().add(vtDmlRecords);
		
		
		VtDmlRecords vtDmlRecordsTwo = new VtDmlRecords();
		vtDmlRecordsTwo.setFields(new ArrayList<>());
		
		VtDmlFields fieldTwo = new VtDmlFields();
		fieldTwo.setName("Non Existing Column");
		fieldTwo.setValue("Name Test");
		
		
		
		
		vtDmlRecordsTwo.getFields().add(fieldTwo);
		vtDmlInsertRequest.getRecords().add(vtDmlRecordsTwo);
		
		
		VtDmlRecords vtDmlRecordsThree = new VtDmlRecords();
		vtDmlRecordsThree.setFields(new ArrayList<>());
		
		VtDmlFields fieldThree = new VtDmlFields();
		fieldThree.setName("Column 1");
		fieldThree.setValue(null);
		
		
		
		
		vtDmlRecordsThree.getFields().add(fieldThree);
		vtDmlInsertRequest.getRecords().add(vtDmlRecordsThree);
		
		
		vtDmlInsertResponse = new VtDmlInsertResponse();
		vtDmlInsertResponse.setRowsInserted(1);
		
		
	}
	
	@Test
	public void testProcessDmlInsert(){
		Mockito.when(vtDmlDao.getVColumns(new Integer(1))).thenReturn(virtualTableFreeQueryResponse);
		Mockito.when(vtDmlDao.insertData(new VtDmlInsertHelperDto())).thenReturn(1);
		vtDmlInsertService.processDmlInsert(vtDmlInsertRequest);
		
		
	}
	
	
	
}
