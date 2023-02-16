package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.FrisokDao;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvnhDao;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxhDao;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.mapper.SvxhMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jul, 2021
 * 
 */
public class SvnhDaoServicesImpl implements SvnhDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SvnhDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	
	public List getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	/**
	 * 
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		//N/A
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	public List findById (Object daoObj, StringBuffer errorStackTrace ){
		List<SvnhDao> retval = new ArrayList<SvnhDao>();
		try{
			SvnhDao dao = (SvnhDao)daoObj;
			
			StringBuffer sql = new StringBuffer();
			
			sql.append(" select * from svnh ");
			sql.append(" where tiavd = ? ");
			sql.append(" and titdn = ? ");
			sql.append(" and tisg = ? ");
			//logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { dao.getTiavd(), dao.getTitdn(), dao.getTisg()  }, new SvxhMapper());
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	
	
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		//N/A
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		//N/A
		
		return retval;
	}
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SvxhDao dao = (SvxhDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE svnh SET tist = ?  ");
			//id's
			sql.append(" WHERE tiavd = ? ");
			sql.append(" AND titdn = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					    dao.getThst(), 
						//id's
						dao.getThavd(),
						dao.getThtdn(),
						dao.getThtuid()
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		//N/A
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
