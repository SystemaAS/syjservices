package no.systema.jservices.tds.z.maintenance.felles.controller.rules;

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
			//TODO !!
			/*
			if( (dao.getDktf_0004t()!=null && !"".equals(dao.getDktf_0004t())) &&
				(dao.getDktf_0010t()!=null && !"".equals(dao.getDktf_0010t())) && 
				(dao.getDktf_0022t()!=null && !"".equals(dao.getDktf_0022t())) && 
				(dao.getDktf_ftipt()!=null && !"".equals(dao.getDktf_ftipt())) &&
				(dao.getDktf_ftust()!=null && !"".equals(dao.getDktf_ftust())) &&
				(dao.getDktf_ftipt()!=null && !"".equals(dao.getDktf_ftipt())) &&
				(dao.getDktf_ftpwt()!=null && !"".equals(dao.getDktf_ftpwt())) &&
				//PROD
				(dao.getDktf_0004p()!=null && !"".equals(dao.getDktf_0004p())) &&
				(dao.getDktf_0010p()!=null && !"".equals(dao.getDktf_0010p())) && 
				(dao.getDktf_0022p()!=null && !"".equals(dao.getDktf_0022p())) && 
				(dao.getDktf_ftipp()!=null && !"".equals(dao.getDktf_ftipp())) &&
				(dao.getDktf_ftusp()!=null && !"".equals(dao.getDktf_ftusp())) &&
				(dao.getDktf_ftipp()!=null && !"".equals(dao.getDktf_ftipp())) &&
				(dao.getDktf_ftpwp()!=null && !"".equals(dao.getDktf_ftpwp()))  ){
			
			}else{
				retval = false;
			}
			*/	
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
		boolean retval = true;
		// N/A
		return retval;
	}
}
