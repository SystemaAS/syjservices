package no.systema.jservices.tds.ncts.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;

import lombok.Data;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Apr, 2021
 * 
 * Aux dao for those AS400-RPG services that must be completed with java services
 * Some AS400-RPG services does not exist and are better implemented with java services right here
 * 
 * This dao has been created in order to be able to delete all item lines at once (SQL)
 * 
 */
@Data
public class SvxvDao implements Serializable, IDao {
		                       								
	private String tvavd = ""; //   SONET        4  0      SYSPED Avdeling   
	private String tvtdn = ""; //   SONET        7  0      SYSPED Oppdragsnr  
	
		
}
