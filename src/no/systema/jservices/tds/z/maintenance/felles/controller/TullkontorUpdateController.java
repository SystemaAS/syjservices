package no.systema.jservices.tds.z.maintenance.felles.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





/**
 * Gateway to the TDS Application
 * 
 * This controller updates the db-table SVXKODF with the xml file from EU-headquarters regarding Tullkontor code, city, etc
 * 
 * @author oscardelatorre
 *
 */

@Controller
public class TullkontorUpdateController {
	

	@Autowired
	RestTemplate restTemplate;
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(TullkontorUpdateController.class.getName());
	private final String FILE_RESOURCE_PATH = "/WEB-INF/resources/files/";
	
	/**
	 * Test this first with the Tinny.xml by changing the name in webapps folder in production. 
	 * (1) Let: COL-Generic-20221223.xml be COL-Generic-20221223XXX.xml
	 * 		and COL-Generic-20221223Tinny.xml be COL-Generic-20221223.xml
	 * (2) Check the Tinny file without sending a user in the url. You should see the log file with correct output.
	 * (3) After being satisfied with the ...Tinny.xml change back to the normal and run again this time with the user:
	 * 		http://xxxxx:yyyyy/syjservices/syjsTullkontorUpdate.do?user=YOUR_USER
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 * http://localhost:8080/syjservices/syjsTullkontorUpdate.do
	 */
	@RequestMapping(value="syjsTullkontorUpdate.do", method={RequestMethod.GET})
	@ResponseBody
	public String tdsgate(HttpSession session, HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		String user = request.getParameter("user");
		//logger.warn("ServerScheme:" + request.getScheme());
		//logger.warn("ServerName:" + request.getServerName());
		//logger.warn("ServerPort:" + request.getServerPort());
		//logger.warn("Root:" + request.getRequestURI());
		StringBuilder serverRootSb = new StringBuilder();
		serverRootSb.append (request.getScheme() + "://" + request.getServerName());
		if(request.getServerPort()>0) {
			serverRootSb.append( ":" + request.getServerPort());
		}
		String serverRoot = serverRootSb.toString();
		logger.warn("ServerRoot:" + serverRoot);
		
		
		String[] LAND_CODES = {"AD", "BE", "BG", "CY", "DK", "EE", "FI","FR","XI", "GB", "GR", 
				"IE", "IS", "IT", "HR", "LV", "LT", "LU", "MT", "NL", "MK", "NO", 
				"PL", "PT", "RO", "SM", "CH", "RS", "SK", "SI", "ES", "SE", "CZ", 
				"TR", "DE", "UA", "HU", "AT"} ;
		List LAND_CODES_LIST = Arrays.asList(LAND_CODES);
		
		try {
		logger.warn("START");
		InputStream inputFile = session.getServletContext().getResourceAsStream(FILE_RESOURCE_PATH + "COL-Generic-20221223.xml");
		
		SAXReader reader = new SAXReader();
        Document document = reader.read( inputFile );

        logger.warn("Root element :" + document.getRootElement().getName());
        //Element root = document.getRootElement();
        //List<Node> nodes = document.selectNodes(".//");
        List<Node> target = document.selectNodes("/ns6:RDEntityList/ns0:RDEntity/ns1:RDEntry" );
        logger.warn("----------------------------");
        SvxkodfDto dto = null;
        List firstList = new ArrayList();
        for(Node node: target) {
        	boolean validLand = false;
        	for(Node child : node.selectNodes("./*")){
        		String refnr = child.valueOf("@name");
        		if(refnr!=null && refnr.equalsIgnoreCase("ReferenceNumber")) {
        			String validLandPrefix = child.getText().substring(0,2);
        			if(LAND_CODES_LIST.contains(validLandPrefix)) {
        				validLand = true;
        				//logger.warn("landPrefix:" + validLandPrefix);
        				//-------------------
	        			//(1) get Tullkontor
	        			//-------------------
	        			dto = new SvxkodfDto();
		        		dto.setTkkode(child.getText());
        			}
        			
        		}
        		if(validLand) {
	        		if(child.getName()!=null && child.getName().equalsIgnoreCase("dataGroup")) {
	        			outer: for(Node child_2 : child.selectNodes("./*")){
	        				//-------------------
	        				//(2)get texts
	        				//-------------------
	        				String name = child_2.valueOf("@name");
	        				if(name!=null && name.equalsIgnoreCase("city")) {
	        					//logger.warn("Current Element (dataGroup) :" + child_2.getName() + " Value:" + child_2.getText());
	        					dto.setTktxtn(child_2.getText().toUpperCase());
	        					dto.setTktxte(dto.getTktxtn());
	        					
	        				}else if(child_2.getName()!=null && child_2.getName().equalsIgnoreCase("dataGroup")) {
	        					//----------------------------------------------
	        					//(3) get roles: Departure, Destination, Transit
	        					//----------------------------------------------
	        					for(Node child_3 : child_2.selectNodes("./ns2:dataGroup/*")){
	        						String role = child_3.valueOf("@name");
	        						if(role!=null && role.equalsIgnoreCase("role")) {
	        							//System.out.println("Current Element (dataGroup-3) :" + child_3.getName() + " Value:" + child_3.getText());
	        							if("TRA".equals(child_3.getText()) ) {
	        								//System.out.println("01:" + child_3.getText());
	        								dto.setTktrs("J");
	        							}else if("DES".equals(child_3.getText()) ) {
	        								//System.out.println("02:" + child_3.getText());
	        								dto.setTkank("J");
	        							}else if("DEP".equals(child_3.getText()) ) {
	        								//System.out.println("03:" + child_3.getText());
	        								dto.setTkavg("J");
	        							}
	        						}
	        					}
	        					//save
	            				if(!dto.getTktxtn().isEmpty()) {
	            					firstList.add(dto);
	            					logger.warn(dto.toString());
	            					//TODO here update to db...SVXKODF
	            					if(StringUtils.isNotEmpty(user)) {
	            						this.updateSvxkodf(dto, user, serverRoot);
	            					}
	            					
	            					break outer;
	            				}
	        					
	        				}
	        				
	        			}
	        			
	        		}
        		}
        		
        	}
        	
        }
        //logger.warn(firstList.toString());
        
        
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		sb.append("OK");
		
		return sb.toString();
		
	}
	
	/**
	 * 
	 * @param dto
	 * @param user
	 * @param serverRoot
	 * @return
	 */
	public int updateSvxkodf(SvxkodfDto dto, String user, String serverRoot) {
		int result = 0;
		String UPDATE_MODE = "U";
		
	    //map dto for rest queryParams... (ObjectMapper has issues with empty strings despite DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY...)
		LinkedMultiValueMap<String, String> map = new UrlRequestParameterMapper().getUrlParameterEdimEdisMap(dto);
		logger.info(map.toString());
		
		URI uri = UriComponentsBuilder
				.fromUriString(serverRoot)
				.path("/syjservices/syjsSVX001R_UC.do")
				.queryParam("user", user)
				.queryParam("mode", UPDATE_MODE)
				.queryParams(map)
				.build()
				.encode()
				.toUri();
		
		try {
			HttpHeaders headerParams = new HttpHeaders();
			headerParams.add("Accept", "");
			HttpEntity<?> entity = new HttpEntity<>(headerParams);
		
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
			String json = response.getBody();
			logger.info(json);
			///Json Mapper (RestTemplate only working with String.class)
			ObjectMapper mapper = new ObjectMapper();
			GenericDtoContainer dtoContainer = mapper.readValue(json, GenericDtoContainer.class);
			
			//at this point the dtoContainer has an error or not
			if( dtoContainer!=null && StringUtils.isNotEmpty(dtoContainer.getErrMsg()) ) {
				logger.error("updateSvxkodf ERROR REST-http-response:" + dtoContainer.getErrMsg());
				result = -1;
			}else {
				logger.warn("updateSvxkodf REST-http-response:" + response.getStatusCodeValue());
			}
			
			
		}catch(Exception e) {
			logger.error(e.toString());
		}
		
		return result;
	}
	
	
	
	
}

