package no.systema.jservices.tds.z.maintenance.felles.controller;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class SvxkodfDto {
	String tkunik = "106";//     tegn            3       3         1        begge    unik kode              
	String tkkode ="";//     tegn           10      10         4        begge    kode                    
	String tktxtn = "";//        tegn           35      35        14        begge    norsk tekst                   
	String tktxte = "";//         tegn           35      35        49        begge    engelsk tekst                        
	String tkavg = "N";//          tegn            1       1        84        begge    avgang                                    
	String tkank = "N";//          tegn            1       1        85        begge    ankomst                      
	String tktrs = "N";//          tegn            1       1        86        begge    transitt 
	                   
	/**
	 * Required for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
