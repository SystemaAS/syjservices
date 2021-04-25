package no.systema.jservices.tds.ncts.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tds.ncts.model.dao.entities.SvxvDao;
import no.systema.jservices.tds.ncts.model.dao.mapper.SvxvMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr, 2021
 * 
 */
public class SvxvDaoServicesImpl implements SvxvDaoServices {
	private static Logger logger = Logger.getLogger(SvxvDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int deleteAll(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			SvxvDao dao = (SvxvDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from svxv ");
			sql.append(" WHERE tvavd = ? ");
			sql.append(" AND tvtdn = ? ");
			logger.warn(sql.toString());
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTvavd(), dao.getTvtdn() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
