package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Jun 23, 2017
 * 
 */
public interface SvxghDaoServices extends IDaoServices { 
	public List findByIdExactMatch (String id, StringBuffer errorStackTrace );
	public int adjustBruktGuarantee (Object obj, StringBuffer errorStackTrace );
}
