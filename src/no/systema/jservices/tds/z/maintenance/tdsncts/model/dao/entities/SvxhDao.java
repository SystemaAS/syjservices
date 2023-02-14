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
	//NCTS 5
	private String thtrnr = ""; //MRN
	private String thtrdt = ""; //MRN date
	private String thtuid = ""; //LRN
	private String thpkl = "";
	private String thws = "";
	private String thddtk = "0"; //SONET 8
	private String thdats = "";
	private String thdkav = "";
	private String thdksk = "";
	private String thtssd = "";
	private String thtsn1 = "";
	private String thtsn2 = "";
	private String thtspn = "";
	private String thtsps = "";
	private String thtslk = "";
	private String thtssk = "";
	private String thtet = "0";
	private String thomd = "0"; //SONET 1
	private String thbind = "";
		
	
	
	
}
