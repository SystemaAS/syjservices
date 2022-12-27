/**
 * 
 */
package no.systema.jservices.tds.z.maintenance.felles.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;



/**
 * @author oscardelatorre
 * @param SEp 2021
 * 
 */
public class UrlRequestParameterMapper {
	private static final Logger logger = LoggerFactory.getLogger(UrlRequestParameterMapper.class.getName());
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object JsonAbstractGrandFatherRecord
	 * @return String, in url format.
	 * 
	 */
	public LinkedMultiValueMap<String, String> getUrlParameterEdimEdisMap(SvxkodfDto object){
		
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						//Nothing
					}else{
						map.add(field.getName(), value );
					}
				}catch(Exception e){
					logger.warn("prstack", e);
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	
	
}
