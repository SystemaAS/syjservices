package no.systema.jservices.tds.controller.rules;
import no.systema.main.util.DateTimeManager;
import java.util.*;
import java.text.*;

import org.apache.logging.log4j.*;
import org.springframework.stereotype.Component;

import no.systema.jservices.controller.JsonResponseOutputterController;
import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.model.dao.services.Svtx08f2_CountryCodeRegionDaoServices;
import no.systema.jservices.tds.model.dao.services.Svtx15f_ExtraMangdDaoServices;


/**
 * RULE class for
 *  
 * FreeForm Source:
 * 	 File: 		QRPGSRC
 * 	 Library:	SYTTAX
 * 	 Member: 	SVT010R: TDS  Finnes X-tra m�ngdenhet ? 
 * 
 * @author oscardelatorre
 * @date Jan 12, 2016
 * 
 */
public class Svt010R {
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private Svtx15f_ExtraMangdDaoServices svtx15f_ExtraMangdDaoServices;
	private Svtx08f2_CountryCodeRegionDaoServices svtx08f2_CountryCodeRegionDaoServices;
	private static Logger logger = LogManager.getLogger(JsonResponseOutputterController.class.getName());
	
	public Svt010R (Svtx15f_ExtraMangdDaoServices extraMangdService, Svtx08f2_CountryCodeRegionDaoServices countryCodeService){
		this.svtx15f_ExtraMangdDaoServices = extraMangdService;
		this.svtx08f2_CountryCodeRegionDaoServices = countryCodeService;
	
	}
	
	
	/**
	 * 
	 * @param list
	 * @param countryCode
	 * @return
	 * @throws Exception
	 */
	public List<GenericTableColumnsDao> applyRules (List<GenericTableColumnsDao> list, String landKode) throws Exception{
		List<GenericTableColumnsDao> newList = new ArrayList();
		
		if(list!=null){
			String maxDate = null;
			String minDate = null;
			String countryCodeRegion = null;
			
			for(GenericTableColumnsDao record: list){
				countryCodeRegion = record.getCol_01();
				maxDate = record.getCol_02(); 
				minDate = record.getCol_03();
				//Rule 01 - check valid current date
				if(this.dateTimeMgr.isValidCurrentDayBeforeLimit(maxDate, DateTimeManager.DB_FORMAT) && this.dateTimeMgr.isValidCurrentDayAfterLimit(minDate, DateTimeManager.DB_FORMAT)){
					logger.info("Date is valid");
					logger.info("landKode:" + landKode + " countryCodeRegion:" + countryCodeRegion);
					if(landKode!=null){
						if(landKode.equals(countryCodeRegion)){
							logger.info("Landkod = countryCoderRegion...");
							List<GenericTableColumnsDao> resultSubQuery = this.svtx08f2_CountryCodeRegionDaoServices.getLandRecord(landKode);
							if(resultSubQuery!=null && resultSubQuery.size()>0){
								newList.add(record);
								break;
							}
							//TODO	
						}else{
							logger.info("Landkod <> countryCoderRegion...");
							List<GenericTableColumnsDao> resultSubQuery = this.svtx08f2_CountryCodeRegionDaoServices.getLandRegionRecord(landKode, countryCodeRegion);
							if(resultSubQuery!=null && resultSubQuery.size()>0){
								newList.add(record);
								break;
							}
						}
					}
				}
			}
			
		}
		return newList;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentDate(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
}
