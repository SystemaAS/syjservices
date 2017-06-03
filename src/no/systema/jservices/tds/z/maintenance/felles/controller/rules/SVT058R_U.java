package no.systema.jservices.tds.z.maintenance.felles.controller.rules;

import org.springframework.validation.ValidationUtils;

import no.systema.jservices.common.dao.SvtlvDao;
import no.systema.jservices.common.util.StringUtils;

/**
 * 
 * @author oscardelatorre
 * @date Jun 03, 2017
 */
public class SVT058R_U {
	private final StringUtils strUtils = new StringUtils();

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SvtlvDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (this.strUtils.isNotNull(dao.getSvlv_kd())) ){
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
	public boolean isValidInputForDelete(SvtlvDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			if( (this.strUtils.isNotNull(dao.getSvlv_kd())) ){
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
	public void updateNumericFieldsIfNull(SvtlvDao dao){
		String ZERO = "0";
		
		//SONET (Decimals)
		if(this.strUtils.isNull(dao.getSvlv_fsp())){
			dao.setSvlv_fsp(ZERO);
		}else{
			String tmp = dao.getSvlv_fsp().replace(",", ".");
			dao.setSvlv_fsp(tmp);
		}
		if(this.strUtils.isNull(dao.getSvlv_fs2p())){
			dao.setSvlv_fs2p(ZERO);
		}else{
			String tmp = dao.getSvlv_fs2p().replace(",", ".");
			dao.setSvlv_fs2p(tmp);
		}
	}
}
