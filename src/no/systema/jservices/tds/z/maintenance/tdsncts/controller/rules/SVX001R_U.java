package no.systema.jservices.tds.z.maintenance.tdsncts.controller.rules;

import org.apache.logging.log4j.*;

import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxkodfDao;
/**
 * 
 * @author oscardelatorre
 * @date Jun 22, 2017
 */
public class SVX001R_U {
	private static Logger logger = LogManager.getLogger(SVX001R_U.class.getName());
	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SvxkodfDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) &&
				(dao.getTkunik()!=null && !"".equals(dao.getTkunik())) ){
			//check dao
			if( (dao.getTkkode()!=null && !"".equals(dao.getTkkode())) && (dao.getTktxtn()!=null && !"".equals(dao.getTktxtn())) ){
				//OK
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
	public boolean isValidInputForDelete(SvxkodfDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTkunik()!=null && !"".equals(dao.getTkunik())) &&
				(dao.getTkkode()!=null && !"".equals(dao.getTkkode()))	){
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
