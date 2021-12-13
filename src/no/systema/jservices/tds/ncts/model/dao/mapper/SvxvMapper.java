package no.systema.jservices.tds.ncts.model.dao.mapper;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tds.ncts.model.dao.entities.SvxvDao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date  Apr, 2021
 * 
 */
public class SvxvMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(SvxvMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SvxvDao dao = new SvxvDao();
    	//We use reflection since there are many fields. We could have written all fields manually without reflection. Refer to other daos.
		try{
	    	Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			for(Field field : list){
				String name = (String)field.getName();
				if(name!=null && !"".equals(name)){
					//DEBUG --> logger.info(field.getName() + " Name:" + name + " value:" + rs.getString(name));
				}
				try{
					//here we put the value
					field.setAccessible(true);
					field.set(dao, rs.getString(name));
				}catch (Exception e){
					//Usually when no column matches the JavaBean property...
					logger.info(e.getMessage() + e.toString());
					continue;
				}
			}
    	}catch(Exception e){
    		e.toString();
    		logger.info(e.getMessage() + e.toString());
    	}
    	
    	
        return dao;
    }

}


