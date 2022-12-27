package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Jun 22, 2016
 * 
 */
public interface SvxkodfDaoServices extends IDaoServices { 
	public List getList(String code, StringBuffer errorStackTrace);
	public List findById(String code, String id, StringBuffer errorStackTrace);
	public int updateComplete(Object daoObj, StringBuffer errorStackTrace);
}
