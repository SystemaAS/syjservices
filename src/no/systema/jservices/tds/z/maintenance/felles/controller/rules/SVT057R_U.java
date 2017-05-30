package no.systema.jservices.tds.z.maintenance.felles.controller.rules;

import org.springframework.validation.ValidationUtils;

import no.systema.jservices.common.dao.SvtvkDao;
import no.systema.jservices.common.util.StringUtils;

/**
 * 
 * @author oscardelatorre
 * @date Maj 19, 2017
 */
public class SVT057R_U {
	private final StringUtils strUtils = new StringUtils();

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
			if( (this.strUtils.isNotNull(dao.getSvvk_kd())) &&
				(this.strUtils.isNotNull(dao.getSvvk_dts())) && 
				(this.strUtils.isNotNull(dao.getSvvk_omr())) && 
				(this.strUtils.isNotNull(dao.getSvvk_krs())) ){
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
			if( (this.strUtils.isNotNull(dao.getSvvk_kd())) &&
				(this.strUtils.isNotNull(dao.getSvvk_dts())) ){
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
		//Integers
		if(this.strUtils.isNull(dao.getSvvk_dts())){
			dao.setSvvk_dts(ZERO);
		}
		if(this.strUtils.isNull(dao.getSvvk_dte())){
			dao.setSvvk_dte(ZERO);
		}
		if(this.strUtils.isNull(dao.getSvvk_omr())){
			dao.setSvvk_omr(ZERO);
		}
		
		//SONET (10,6)
		if(this.strUtils.isNull(dao.getSvvk_krs())){
			dao.setSvvk_krs(ZERO);
		}else{
			String tmp = dao.getSvvk_krs().replace(",", ".");
			dao.setSvvk_krs(tmp);
		}
	}
}
