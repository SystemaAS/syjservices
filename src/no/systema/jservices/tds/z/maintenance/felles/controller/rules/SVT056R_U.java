package no.systema.jservices.tds.z.maintenance.felles.controller.rules;

import org.springframework.validation.ValidationUtils;

import no.systema.jservices.common.dao.SvthaDao;

/**
 * 
 * @author oscardelatorre
 * @date Maj 08, 2017
 */
public class SVT056R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SvthaDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSvth_sysg()!=null && !"".equals(dao.getSvth_sysg())) &&
				(dao.getSvth_namn()!=null && !"".equals(dao.getSvth_namn())) && 
				(dao.getSvth_usid()!=null && !"".equals(dao.getSvth_usid()))    ){
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(SvthaDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			if( (dao.getSvth_sysg()!=null && !"".equals(dao.getSvth_sysg()))    ){
				}else{
					retval = false;
				}
		}else{
			retval = false;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param dao
	 */
	public void updateNumericFieldsIfNull(SvthaDao dao){
		String ZERO = "0";
		if(dao.getSvth_pwdd()==null || "".equals(dao.getSvth_pwdd())){
			dao.setSvth_pwdd(ZERO);
		}
	}
}
