package no.systema.jservices.tds.z.maintenance.felles.controller.rules;

import org.springframework.validation.ValidationUtils;

import no.systema.jservices.common.dao.SvtfiDao;
/**
 * 
 * @author oscardelatorre
 * @date Maj 02, 2017
 */
public class SVT055R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SvtfiDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSvtf_0004()!=null && !"".equals(dao.getSvtf_0004())) &&
				(dao.getSvtf_0010()!=null && !"".equals(dao.getSvtf_0010())) && 
				(dao.getSvtf_0022()!=null && !"".equals(dao.getSvtf_0022())) && 
				(dao.getSvtf_pref()!=null && !"".equals(dao.getSvtf_pref())) &&
				(dao.getSvtf_numb()!=null && !"".equals(dao.getSvtf_numb())) &&
				(dao.getSvtf_usri()!=null && !"".equals(dao.getSvtf_usri())) &&
				(dao.getSvtf_usra()!=null && !"".equals(dao.getSvtf_usra())) &&
				//PROD
				(dao.getSvtf_sec1()!=null && !"".equals(dao.getSvtf_sec1())) &&
				(dao.getSvtf_sec2()!=null && !"".equals(dao.getSvtf_sec2())) && 
				(dao.getSvtf_cer1()!=null && !"".equals(dao.getSvtf_cer1())) &&
				(dao.getSvtf_cer2()!=null && !"".equals(dao.getSvtf_cer2())) ){
			
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
	public boolean isValidInputForDelete(SvtfiDao dao, String user, String mode){
		boolean retval = false;
		// N/A
		return retval;
	}
}
