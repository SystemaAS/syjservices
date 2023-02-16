package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;

import lombok.Data;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Feb, 2023
 * 
 */
@Data 
public class SvnhDao implements Serializable, IDao {

	private String tist = "";  //Status 
	private String tiavd = ""; //avd
	private String titdn = ""; //oppd
	private String tisg = ""; //sign
	
	
	
}
