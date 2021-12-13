package no.systema.jservices.tds.model.dao.services;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import no.systema.jservices.controller.JsonResponseOutputterController;
import no.systema.jservices.model.dao.mapper.Svtx08f2_CountryCodeRegionMapper;
import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.tds.model.dao.mapper.Svtx15f_ExtraMangdMapper;


public class Svtx15f_ExtraMangdDaoServicesImpl implements Svtx15f_ExtraMangdDaoServices {
	private static Logger logger = LogManager.getLogger(Svtx15f_ExtraMangdDaoServicesImpl.class.getName());
	
	/**
	 * 
	 * @return
	 */
	public List<GenericTableColumnsDao> getMainList(String itemNo){
		/*String sql = "select knavn, adr1, adr2, postnr, adr3 from syspedf/cundf  where knavn like ?";
		String paramKnavn = "B%";
		final Object[] params = new Object[]{ paramKnavn }; 
        return this.jdbcTemplate.query( sql, params, new CundfMapper());
        */
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select LTRIM(RTRIM(svtx15_02)) svtx15_02, svtx15_10, svtx15_11, svtx15_33");
		sql.append(" from svtx15f");
		sql.append (" where svtx15_01 in(?, ?, ?, ?, ?)");
		sql.append (" and svtx15_04 in(?, ?, ?)");
		sql.append (" and svtx15_33<>?");
		//svtx15_01 SQL
		String param1 = itemNo;
		String param2 = itemNo.substring(0,8) + "00";
		String param3 = itemNo.substring(0,6) + "0000";
		String param4 = itemNo.substring(0,4) + "000000";
		String param5 = itemNo.substring(0,2) + "00000000";
		//svtx15_04 SQL
		String param6 = "109";
		String param7 = "110";
		String param8 = "111";
		String param9 = " ";
		//Param list
		final Object[] params = new Object[]{ param1, param2, param3, param4, param5,  param6, param7, param8, param9 };
		//Mapper
		return this.jdbcTemplate.query( sql.toString(), params,  new Svtx15f_ExtraMangdMapper());
	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
