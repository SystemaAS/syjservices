package no.systema.jservices.model.dao.mapper;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Jan 7, 2016
 * 
 */
public class Svtx08f2_CountryCodeRegionMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(Svtx08f2_CountryCodeRegionMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	GenericTableColumnsDao dbTable = new GenericTableColumnsDao();
    	dbTable.setCol_01(rs.getString("svtx082_01"));

    	/*
    	try{
    		rs.findColumn("svtx082_02");
    		dbTable.setCol_01(rs.getString("svtx082_02"));
    	}catch(Exception e){
    		//if the column does not exist (which could be the case the special case of this Mapper when only the country code is sent)
    	}
    	*/
    	
        return dbTable;
    }

}


