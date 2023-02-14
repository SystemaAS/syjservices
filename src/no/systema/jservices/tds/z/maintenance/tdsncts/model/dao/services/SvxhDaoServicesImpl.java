package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.FrisokDao;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxhDao;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.mapper.SvxhMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jul, 2021
 * 
 */
public class SvxhDaoServicesImpl implements SvxhDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SvxhDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	public int releaseGuarantee (Object daoObj, StringBuffer errorStackTrace ) {
		int retval = 0;
		try{
			SvxhDao dao = (SvxhDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE svxh SET thgbl = thgbl * -1  ");
			
			//id's
			sql.append(" WHERE thavd = ? ");
			sql.append(" AND thtdn = ? ");
			sql.append(" AND thsg = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						//id's
						dao.getThavd(),
						dao.getThtdn(),
						dao.getThsg()
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
	 * returns all the opd that are still reserving guaranty 
	 */
	public List getListReservedGuarantee (StringBuffer errorStackTrace){
		List<SvxhDao> retval = new ArrayList<SvxhDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE thgbl > 0 ");
			sql.append(" AND (thst = 'P' or thst = 'Z') ");
			
			//logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new SvxhMapper());
			
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
	 * This will find all current reserved valid guarantees. Those that can not be released yet
	 */
	public List getListReservedGuaranteeWithId ( String id, StringBuffer errorStackTrace ) {
		List<SvxhDao> retval = new ArrayList<SvxhDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" WHERE thgbl > 0 ");
			sql.append(" AND thgft1 = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id },  new SvxhMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.error(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
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
		List<SvxhDao> retval = new ArrayList<SvxhDao>();
		try{
			SvxhDao dao = (SvxhDao)daoObj;
			
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where thavd = ? ");
			sql.append(" and thtdn = ? ");
			sql.append(" and thsg = ? ");
			//logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { dao.getThavd(), dao.getThtdn(), dao.getThsg()  }, new SvxhMapper());
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
		
		try{
			SvxhDao dao = (SvxhDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE svxh SET thgbl = ?  ");
			
			//id's
			sql.append(" WHERE thavd = ? ");
			sql.append(" WHERE thtdn = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getThgbl(), 
						//id's
						dao.getThavd(),
						dao.getThtdn()
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
	 * Update MRN for 028
	 * NCTS 5 - Feb.2023
	 */
	public int updateMrn028(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SvxhDao dao = (SvxhDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE svxh SET thst = ?, thtrnr = ?, thtrdt = ?  ");
			//id's
			sql.append(" WHERE thavd = ? ");
			sql.append(" AND thtdn = ? ");
			sql.append(" AND thtuid = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					    dao.getThst(), dao.getThtrnr(), dao.getThtrdt(),   
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
	 * Update MRN for 029
	 * NCTS 5 - Feb.2023
	 */
	public int updateMrn029(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SvxhDao dao = (SvxhDao)daoObj;
			logger.warn(dao.toString());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE svxh SET thst = ?, thpkl = ?, thws = ?, thddt = ?, thddtk = ?, thdats = ?, thdkav = ?, thdksk = ?,  ");
			sql.append(" thtssd = ?, thtsn1 = ?, thtsn2 = ?, thtspn = ?, thtsps = ?, thtslk = ?, thtssk = ?, thtet = ?,  ");
			sql.append(" thomd = ?, thbind = ?  ");
			//id's
			sql.append(" WHERE thavd = ? ");
			sql.append(" AND thtdn = ? ");
			sql.append(" AND thtuid = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getThst(), dao.getThpkl(), dao.getThws(), dao.getThddt(), dao.getThddtk(), dao.getThdats(), dao.getThdkav(), dao.getThdksk(),
						dao.getThtssd(), dao.getThtsn1(), dao.getThtsn2(), dao.getThtspn(), dao.getThtsps(), dao.getThtslk(), dao.getThtssk(), dao.getThtet(),
						dao.getThomd(), dao.getThbind(),
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
	
	public int updateMrn045(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SvxhDao dao = (SvxhDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE svxh SET thst = ?  ");
			//id's
			sql.append(" WHERE thavd = ? ");
			sql.append(" AND thtdn = ? ");
			sql.append(" AND thtuid = ? ");
			
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
	 * 
	 * @return
	 */
	private StringBuffer getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT thst,thavd,thtdn,thsg,thdt,thddt,thgft1,thgadk,thgbl,thgvk  from svxh ");
		
		
		return sql;
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
