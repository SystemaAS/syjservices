package no.systema.jservices.model.dao.services;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import no.systema.jservices.controller.JsonResponseOutputterController;
import no.systema.jservices.model.dao.mapper.Svtx08f2_CountryCodeRegionMapper;
import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.tds.model.dao.mapper.Svtx15f_ExtraMangdMapper;


public class Svtx08f2_CountryCodeRegionDaoServicesImpl implements Svtx08f2_CountryCodeRegionDaoServices {
	private static Logger logger = LogManager.getLogger(Svtx08f2_CountryCodeRegionDaoServicesImpl.class.getName());
	
		
	/**
	 * 
	 * @param landKode
	 * @param region
	 * 
	 * @return 
	 */
	public List<GenericTableColumnsDao> getLandRegionRecord(String landKode, String region){
		StringBuffer sql = new StringBuffer();
		sql.append(" select svtx082_01 ");
		sql.append(" from svtx08f2 ");
		sql.append(" where svtx082_01 = ? ");
		sql.append(" and svtx082_02 = ? ");
		 
		String param1 = landKode;
		String param2 = region;
		//Param list
		final Object[] params = new Object[]{ param1, param2 };
		//Mapper
		return this.jdbcTemplate.query( sql.toString(), params,  new Svtx08f2_CountryCodeRegionMapper());
	}
	/**
	 * 
	 * @param landKode
	 * 
	 * @return
	 */
	public List<GenericTableColumnsDao> getLandRecord(String landKode){
		StringBuffer sql = new StringBuffer();
		sql.append(" select svtx082_01");
		sql.append(" from svtx08f2");
		sql.append (" where svtx082_01 = ?");
		
		String param1 = landKode;
		
		//Param list
		final Object[] params = new Object[]{ param1 };
		//Mapper
		return this.jdbcTemplate.query( sql.toString(), params,  new Svtx08f2_CountryCodeRegionMapper());
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
