package no.systema.jservices.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.BridfDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Feb 11, 2016
 * 
 */
public class BridfMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(BridfMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	BridfDao dao = new BridfDao();
    	dao.setBibrid(rs.getString("bibrid"));
    	dao.setBipo(rs.getString("bipo"));
    	dao.setBibesk(rs.getString("bibesk"));
    	
        return dao;
    }

}


