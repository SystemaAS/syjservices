package no.systema.jservices.tds.z.maintenance.tdsncts.controller.rules;

import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxghDao;
/**
 * 
 * @author oscardelatorre
 * @date Jun 23, 2017
 */
public class SVX030R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SvxghDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTggnr()!=null && !"".equals(dao.getTggnr())) && 
				(dao.getTgtina()!=null && !"".equals(dao.getTgtina())) && 
				(dao.getTgnaa()!=null && !"".equals(dao.getTgnaa())) && 
				(dao.getTgpna()!=null && !"".equals(dao.getTgpna())) && 
				(dao.getTgpsa()!=null && !"".equals(dao.getTgpsa())) && 
				(dao.getTglka()!=null && !"".equals(dao.getTglka())) && 
				(dao.getTggty()!=null && !"".equals(dao.getTggty())) && 
				(dao.getTgtsd()!=null && !"".equals(dao.getTgtsd())) && 
				(dao.getTgakny()!=null && !"".equals(dao.getTgakny())) &&
				(dao.getTgakgm()!=null && !"".equals(dao.getTgakgm())) &&
				(dao.getTggbl()!=null && !"".equals(dao.getTggbl())) && 
				(dao.getTggvk()!=null && !"".equals(dao.getTggvk()))
				
				  ){
				
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
	public boolean isValidInputForDelete(SvxghDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTggnr()!=null && !"".equals(dao.getTggnr())) ){
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
	 */
	public void updateNumericFieldsIfNull(SvxghDao dao){
		String ZERO = "0";
		if(dao.getTggty()==null || "".equals(dao.getTggty())){
			dao.setTggty(ZERO);
		}
		if(dao.getTggbl()==null || "".equals(dao.getTggbl())){
			dao.setTggbl(ZERO);
		}
		if(dao.getTggblb()==null || "".equals(dao.getTggblb())){
			dao.setTggblb(ZERO);
		}
		if(dao.getTgkna()==null || "".equals(dao.getTgkna())){
			dao.setTgkna(ZERO);
		}
		if(dao.getTgdt()==null || "".equals(dao.getTgdt())){
			dao.setTgdt(ZERO);
		}
		if(dao.getTgdtr()==null || "".equals(dao.getTgdtr())){
			dao.setTgdtr(ZERO);
		}
		if(dao.getTgprm()==null || "".equals(dao.getTgprm())){
			dao.setTgprm(ZERO);
		}
	}
}
