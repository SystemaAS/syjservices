package no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxghDao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date  Jun 23, 2017
 * 
 */
public class SvxghMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(SvxghMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SvxghDao dao = new SvxghDao();
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


