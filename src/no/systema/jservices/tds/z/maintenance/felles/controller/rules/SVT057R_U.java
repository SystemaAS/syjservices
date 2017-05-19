package no.systema.jservices.tds.z.maintenance.felles.controller.rules;

import org.springframework.validation.ValidationUtils;

import no.systema.jservices.common.dao.SvtvkDao;

/**
 * 
 * @author oscardelatorre
 * @date Maj 19, 2017
 */
public class SVT057R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SvtvkDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSvvk_kd()!=null && !"".equals(dao.getSvvk_kd())) &&
				(dao.getSvvk_dts()!=null && !"".equals(dao.getSvvk_dts())) && 
				(dao.getSvvk_omr()!=null && !"".equals(dao.getSvvk_omr())) && 
				(dao.getSvvk_krs()!=null && !"".equals(dao.getSvvk_krs())) ){
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
	public boolean isValidInputForDelete(SvtvkDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			if( (dao.getSvvk_kd()!=null && !"".equals(dao.getSvvk_kd())) &&
				(dao.getSvvk_dts()!=null && !"".equals(dao.getSvvk_dts())) && 
				(dao.getSvvk_omr()!=null && !"".equals(dao.getSvvk_omr())) ){
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
	public void updateNumericFieldsIfNull(SvtvkDao dao){
		String ZERO = "0";
		
		if(dao.getSvvk_dts()==null || "".equals(dao.getSvvk_dts())){
			dao.setSvvk_dts(ZERO);
		}
		if(dao.getSvvk_dte()==null || "".equals(dao.getSvvk_dte())){
			dao.setSvvk_dte(ZERO);
		}
		if(dao.getSvvk_omr()==null || "".equals(dao.getSvvk_omr())){
			dao.setSvvk_omr(ZERO);
		}
		if(dao.getSvvk_krs()==null || "".equals(dao.getSvvk_krs())){
			dao.setSvvk_krs(ZERO);
		}
	}
}
