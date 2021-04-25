package no.systema.jservices.tds.ncts.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tds.ncts.model.dao.entities.SvxvDao;
/**
 * 
 * @author oscardelatorre
 * @date Apr, 2021
 */
public class SVXV_U {

	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDeleteAll(SvxvDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getTvavd()) && StringUtils.isNotEmpty(dao.getTvtdn()) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		
		return retval;
	}
	
	
	
}
