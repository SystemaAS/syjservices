package no.systema.jservices.tds.z.maintenance.tdsncts.controller.rules;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.*;

import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxhDao;
/**
 * 
 * @author oscardelatorre
 * @date Jul, 2021
 */
public class SVX030R_FBRUKT_U {
	private static Logger logger = LogManager.getLogger(SVX030R_FBRUKT_U.class.getName());
	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SvxhDao dao, String user){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user)){ 
			//OK
			if(StringUtils.isNotEmpty(dao.getThavd()) && StringUtils.isNotEmpty(dao.getThtdn()) && StringUtils.isNotEmpty(dao.getThsg()) ) {
				//OK
			}else {
				retval = false;
			}
			
		}else{
			retval = false;
		}
		return retval;
	}
	
	
}
