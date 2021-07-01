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
 * @date Jul, 2021
 * 
 */
@Data 
public class SvxhDao implements Serializable, IDao {

	private String thst = "";  //Status 
	private String thavd = ""; //avd
	private String thtdn = ""; //oppd
	private String thsg = ""; //sign
	private String thdt = "0"; //date YMD
	private String thgft1 = ""; //garanti
	private String thgadk = ""; //Adgangskode
	private String thgbl = "0"; //Garantibelopp
	private String thgvk = ""; //Valuta
	
	private String thddt = "0"; //Fristdatum
	
	
	
}
