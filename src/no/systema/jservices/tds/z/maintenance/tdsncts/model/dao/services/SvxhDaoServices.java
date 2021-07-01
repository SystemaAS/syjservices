package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services;
import java.util.*;
import no.systema.jservices.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Jul, 2021
 * 
 */
public interface SvxhDaoServices extends IDaoServices {
	public List getListReservedGuarantee ( StringBuffer errorStackTrace );
	public List getListReservedGuaranteeWithId ( String id, StringBuffer errorStackTrace );
	public List findById (Object obj, StringBuffer errorStackTrace );
	public int releaseGuarantee (Object obj, StringBuffer errorStackTrace );
}
