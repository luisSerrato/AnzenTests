package com.citibanamex.mafcs.commoditycatalog.util;

import java.util.regex.Pattern;

import com.citibanamex.mafcs.commoditycatalog.errorhandling.exception.YearFormatException;

public class Util {

private static final String[] SUMM_KEY_METAL = new String[] { "OCEN", "OAZT" };
	
	private static final String[] SUMMARY_KEY_METAL= new String[]{ "OCEN", "OAZT", "O1OZ", "O1HG", "PLIB" };
	
	private static final String[] SUMM_NAME_METAL = new String[] { "hidalgos", "onzasoro", "onzasplata" };
	
	private static final String[] METAL_NAMES = new String[] { "Oro", "Plata" };

	private static final String[] CALC_KEY_CURRENCY= new String[]{ "COR", "DOC", "HOY", "TOM", "SPO" };

	private static final String[] SUMM_KEY_CURRENCY= new String[]{"BIL", "EUR", "JPY", "BRL", "SEK", "CHF"};
	
	private static final String[] CONT_KEY_CURRENCY = new String[] { "America", "Europa", "Asia", "Africa" };
	
	private static final String[] SUMM_KEY_DOLLAR= new String[]{"COR", "DOC", "HOY", "TOM", "SPO"};
	
	public static final String DOUBLE_4_DECIMAL= "#.####";

	public static final String DOUBLE_2_DECIMAL= "#.##";

	public static final String SQL_CONTINENT = "SELECT LTRIM(RTRIM(CD.DIV_CVE)) AS CURRENCY_KEY, P.PAI_BANDERA AS COUNTRY_FLAG, P.PAI_NOM AS COUNTRY_NAME_ES, P.PAI_NOM_ING AS COUNTRY_NAME_EN, CD.DIV_DESC AS CURRENCY_DESC_ES, CD.DIV_DESC_ING AS CURRENCY_DESC_EN, D.DIV_PRE_CPA AS SHOP_PRICE, D.DIV_PRE_VENTA AS SALES_PRICE FROM CAT_DIVISAS CD, CAT_DIVISAS_ADMIN CTA, (SELECT * FROM PAISES UNION SELECT 'EUA','AMERICA','USA','AMERICA','USA.GIF' FROM PAISES) P, DIVISAS D WHERE LTRIM(RTRIM(CD.DIV_CVE))=LTRIM(RTRIM(CTA.DIV_CVE)) AND CD.DIV_PAIS=P.PAI_NOM AND LTRIM(RTRIM(CD.DIV_CVE))=LTRIM(RTRIM(D.DIV_CVE)) AND LTRIM(RTRIM(P.PAI_NOM_CONTINENTE))= ? AND LTRIM(RTRIM(CD.DIV_PAIS_ING))<>'USA' AND CTA.DIV_VISIBLE=1 ORDER BY CTA.DIV_ORDEN, P.PAI_NOM";
	
	public static final String SQL_DOLLAR_SUMMARY="SELECT DISTINCT LTRIM(RTRIM(CD.DIV_CVE)) AS CURRENCY_KEY, P.PAI_BANDERA AS COUNTRY_FLAG, P.PAI_NOM AS COUNTRY_ES, P.PAI_NOM_ING AS COUNTRY_EN, CD.DIV_DESC AS CURRENCY_ES, CD.DIV_DESC_ING AS CURRENCY_EN, D.DIV_PRE_CPA AS BUY_PRICE, D.DIV_PRE_VENTA AS SALE_PRICE FROM CAT_DIVISAS CD, (SELECT * FROM PAISES UNION SELECT 'EUA','AMERICA','USA','AMERICA','USA.GIF' FROM DUAL) P, DIVISAS D WHERE CD.DIV_PAIS=P.PAI_NOM AND LTRIM(RTRIM(CD.DIV_CVE))=LTRIM(RTRIM(D.DIV_CVE)) AND LTRIM(RTRIM(CD.DIV_CVE)) IN (?,?,?,?,?)";
	
	public static final String SQL_DOLLAR="SELECT DISTINCT div_dlr_mes AS h_month, div_dlr_year AS h_year, div_dlr_tpocambio AS h_tchange FROM (SELECT DISTINCT SUB.DIV_DLR_MES, SUB.DIV_DLR_YEAR, SUB.DIV_DLR_TPOCAMBIO, ROW_NUMBER() OVER( PARTITION BY SUB.DIV_DLR_MES, SUB.DIV_DLR_YEAR ORDER BY SUB.div_dlr_year, SUB.DIV_DLR_MES, SUB.DIV_DLR_TPOCAMBIO ) numero FROM HIST_DOLLAR SUB WHERE SUB.div_dlr_year BETWEEN ? AND ?) hd WHERE hd.numero=1 ORDER BY hd.div_dlr_year";
	
	public static final String SQL_SUMMARY = "SELECT DISTINCT LTRIM(RTRIM(CD.DIV_CVE)) AS CURRENCY_KEY, P.PAI_BANDERA AS COUNTRY_FLAG, P.PAI_NOM AS COUNTRY_ES, P.PAI_NOM_ING AS COUNTRY_EN, CD.DIV_DESC AS CURRENCY_ES, CD.DIV_DESC_ING AS CURRENCY_EN, D.DIV_PRE_CPA AS BUY_PRICE, D.DIV_PRE_VENTA AS SALE_PRICE FROM CAT_DIVISAS CD, (SELECT * FROM PAISES UNION SELECT 'EUA','AMERICA','USA','AMERICA','USA.GIF' FROM DUAL) P, DIVISAS D WHERE CD.DIV_PAIS=P.PAI_NOM AND LTRIM(RTRIM(CD.DIV_CVE))=LTRIM(RTRIM(D.DIV_CVE)) AND LTRIM(RTRIM(CD.DIV_CVE)) IN (?,?,?,?,?,?)";
	
	public static final String SQL_CURRENCY ="SELECT DISTINCT LTRIM(RTRIM(CD.div_cve)) AS currency_key, P.pai_nom AS country_es, P.pai_nom_ing AS country_en, CD.div_desc AS currency_es, CD.div_desc_ing AS currency_en, D.div_pre_venta AS sale_price FROM cat_divisas CD, (select * from paises UNION select 'EUA','America','USA','America','usa.gif' FROM paises) P, divisas D WHERE CD.div_pais=P.pai_nom AND LTRIM(RTRIM(CD.div_cve))=LTRIM(RTRIM(D.div_cve)) AND LTRIM(RTRIM(CD.div_cve)) NOT IN (?,?,?,?,?) ORDER BY P.pai_nom";
	
	public static final String SQLBYCOINNAME="SELECT DISTINCT CM.MET_CVE AS METAL_KEY, CM.MET_DESC AS COIN, CM.MET_PESOS_METAL AS PESOS_METAL, M.MET_PRE_CPA AS BUY_PRICE, M.MET_PRE_VENTA AS SALE_PRICE FROM CAT_METALES CM INNER JOIN CAT_METALES_ADMIN CMA ON CM.MET_CVE=CMA.MET_CVE INNER JOIN METALES M ON CM.MET_CVE=M.MET_CVE WHERE LOWER(CM.MET_TIPO)=? AND CMA.MET_VISIBLE=1 ORDER BY M.MET_PRE_VENTA DESC";

	public static final String SQLBYMETALKEYS="SELECT DISTINCT CM.MET_CVE AS METAL_KEY, CM.MET_DESC AS COIN, CM.MET_PESOS_METAL AS PESOS_METAL, M.MET_PRE_CPA AS BUY_PRICE, M.MET_PRE_VENTA AS SALE_PRICE FROM CAT_METALES CM INNER JOIN CAT_METALES_ADMIN CMA ON CM.MET_CVE=CMA.MET_CVE INNER JOIN METALES M ON CM.MET_CVE=M.MET_CVE WHERE  CM.MET_CVE IN (?,?) AND  CMA.MET_VISIBLE=1";
	
	public static final String SQL="SELECT DISTINCT CM.MET_CVE AS METAL_KEY, CM.MET_DESC AS COIN, CM.MET_PESOS_METAL AS PESOS_METAL, M.MET_PRE_CPA AS BUY_PRICE, M.MET_PRE_VENTA AS SALE_PRICE FROM CAT_METALES CM INNER JOIN CAT_METALES_ADMIN CMA ON CM.MET_CVE=CMA.MET_CVE INNER JOIN METALES M ON CM.MET_CVE=M.MET_CVE WHERE CM.MET_CVE IN (?,?,?,?,?)";
	
	public static final String UNAVAILABLE_DATABASE_MESSAGE="Unavailable database micro service";
	
	public static final String NUMBER_LENGHT_MESSAGE= "Number lenght required is 4";
	
	public static final String NUMBER_FORMAT_MESSAGE = "Number format not valid";
	
	/**
	 * COINS_POSITION Dado que las primeras dos monedas (Centenario y Azteca)
	 * tienen estructuras diferentes, se usa la constante para separarlos al
	 * llenar el ArrayList
	 */
	public static final int COINS_POSITION = 2;

	public static final int PARAMS_LENGTH = 1;

	private Util() {
	}
	
	public static String[] getSummKeyDollar() {
		return SUMM_KEY_DOLLAR;
	}

	public static String[] getCalcKeyCurrency() {
		return CALC_KEY_CURRENCY;
	}
	
	public static String[] getSummKeyCurrency() {
		return SUMM_KEY_CURRENCY;
	}
	
	public static String[] getContKeyCurrency() {
		return CONT_KEY_CURRENCY;
	}
	
	public static String[] getSummKeyMetal() {
		return SUMM_KEY_METAL;
	}

	public static String[] getSummNameMetal() {
		return SUMM_NAME_METAL;
	}

	public static String[] getMetalNames() {
		return METAL_NAMES;
	}
	
	public static String[] getSummaryKeyMetal() {
		return SUMMARY_KEY_METAL;
	}

	public static int getMonthNumber(String month){
		return Month.valueOf(month.toUpperCase()).ordinal()+1;
	}
	
	public enum Month{
		ENERO("ENERO"),
		FEBRERO("FEBRERO"),
		MARZO("MARZO"),
		ABRIL("ABRIL"),
		MAYO("MAYO"),
		JUNIO("JUNIO"),
		JULIO("JULIO"),
		AGOSTO("AGOSTO"),
		SEPTIEMBRE("SEPTIEMBRE"),
		OCTUBRE("OCTUBRE"),
		NOVIEMBRE("NOVIEMBRE"),
		DICIEMBRE("DICIEMBRE");
		
		@SuppressWarnings("unused")
		private String text;
		
		private Month(final String text){
			this.text=text;
		}
	}
	
	public static int validateLenghtInt(int value){
		final Pattern pattern = Pattern.compile("^\\d{4}$");
		
		if(!pattern.matcher(String.valueOf(value)).matches()){
			throw new YearFormatException(NUMBER_LENGHT_MESSAGE);
		}
		return value;
	}
	
}
