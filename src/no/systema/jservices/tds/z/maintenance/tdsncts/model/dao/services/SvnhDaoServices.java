package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Feb, 2023
 * 
 */
public interface SvnhDaoServices extends IDaoServices {
	
	public List findById (Object daoObj, StringBuffer errorStackTrace );
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace);
	
}
