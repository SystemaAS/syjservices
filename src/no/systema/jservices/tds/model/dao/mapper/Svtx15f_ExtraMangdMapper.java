package no.systema.jservices.tds.model.dao.mapper;

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
public class Svtx15f_ExtraMangdMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(Svtx15f_ExtraMangdMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	GenericTableColumnsDao dbTable = new GenericTableColumnsDao();
    	dbTable.setCol_01(rs.getString("svtx15_02"));
    	dbTable.setCol_02(rs.getString("svtx15_10"));
    	dbTable.setCol_03(rs.getString("svtx15_11"));
    	dbTable.setCol_04(rs.getString("svtx15_33"));
    	
    	//DEBUG-->logger.info(cusdf.getKnavn());
    	
        return dbTable;
    }

}


