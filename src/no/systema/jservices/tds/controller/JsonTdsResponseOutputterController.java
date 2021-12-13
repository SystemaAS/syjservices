package no.systema.jservices.tds.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import org.apache.logging.log4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Application
import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.model.dao.services.Svtx08f2_CountryCodeRegionDaoServices;
import no.systema.jservices.tds.controller.rules.Svt010R;
import no.systema.jservices.tds.model.dao.services.Svtx15f_ExtraMangdDaoServices;


/**
 * Json Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 */

@Controller
public class JsonTdsResponseOutputterController {
	private static Logger logger = LogManager.getLogger(JsonTdsResponseOutputterController.class.getName());
	private static String JSON_START = "{";
	private static String JSON_END = "}";
	private static String JSON_QUOTES = "\"";
	private static String JSON_RECORD_SEPARATOR = ",";
	private static String JSON_FIELD_SEPARATOR = ",";
	
	private static String JSON_OPEN_LIST = "[";
	private static String JSON_CLOSE_LIST = "]";
	private static String JSON_OPEN_LIST_RECORD = "{";
	private static String JSON_CLOSE_LIST_RECORD = "}";
	
	
	/**
	 * FreeForm Source:
	 * 	 File: 		QRPGSRC
	 * 	 Library:	SYTTAX
	 * 	 Member: 	SVT010R: TDS  Finnes X-tra m�ngdenhet ?
	 *  
	 *   Replaces:  TSVG016R.pgm (RPG-module)
	 * 
	 * @return
	 * @Example http://localhost:8080/syjservices/syjsSVT010R.do?user=OSCAR&kod=1702907900&lk=PE (UC2 : -->kod=6601992000&lk=NO )
	 * 
	 */
	@RequestMapping(value="syjsSVT010R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsSVT010R(HttpSession session, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		try{
			logger.info("Inside syjsSVT010R.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String itemNo = request.getParameter("kod");
			String lk = request.getParameter("lk");
			
			//get list
			logger.info("Before dao getMainList");
			List<GenericTableColumnsDao> mainList = this.svtx15f_ExtraMangdDaoServices.getMainList(itemNo);
			if(mainList==null){ mainList = new ArrayList(); }
			logger.info("After dao getMainList. List size:" + mainList.size());
			
			//Add extra validation rules since the SQL is not a complex one in the previous step...
			if(mainList.size()>0){
				Svt010R svtObj = new Svt010R(this.svtx15f_ExtraMangdDaoServices, this.getSvtx08f2_CountryCodeRegionDaoServices());
				mainList = svtObj.applyRules(mainList, lk);
			}
			
			//build the return JSON
			sb.append(JSON_START);
			sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
			sb.append(this.setFieldQuotes("kod") + ":" + this.setFieldQuotes(itemNo) + ",");
			sb.append(this.setFieldQuotes("lk") + ":" + this.setFieldQuotes(lk) + ",");
			sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes("") + ",");
			sb.append(this.setFieldQuotes("xtramangdenhet") + ":");
			sb.append(JSON_OPEN_LIST);
			//START RECORD
			sb.append(JSON_OPEN_LIST_RECORD);
			if(mainList.size()>0){
				sb.append(JSON_QUOTES + "xtra" + JSON_QUOTES + ":" + JSON_QUOTES + "Y" + JSON_QUOTES);
			}else{
				sb.append(JSON_QUOTES + "xtra" + JSON_QUOTES + ":" + JSON_QUOTES + "N" + JSON_QUOTES);
			}
			sb.append(JSON_CLOSE_LIST_RECORD);
			//END RECORD
			sb.append(JSON_CLOSE_LIST);
			sb.append(JSON_END);
			
		}catch(Exception e){
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String setFieldQuotes(String value){
		String retval = value;
		retval = this.JSON_QUOTES + value + this.JSON_QUOTES;
		
		return retval;
	}
	
	
	@Qualifier ("svtx15f_ExtraMangdDaoServices")
	private Svtx15f_ExtraMangdDaoServices svtx15f_ExtraMangdDaoServices;
	@Autowired
	@Required
	public void setSvtx15f_ExtraMangdDaoServices (Svtx15f_ExtraMangdDaoServices value){ this.svtx15f_ExtraMangdDaoServices = value; }
	public Svtx15f_ExtraMangdDaoServices getSvtx15f_ExtraMangdDaoServices(){ return this.svtx15f_ExtraMangdDaoServices; }
	
	
	@Qualifier ("svtx08f2_CountryCodeRegionDaoServices")
	private Svtx08f2_CountryCodeRegionDaoServices svtx08f2_CountryCodeRegionDaoServices;
	@Autowired
	@Required
	public void setSvtx08f2_CountryCodeRegionDaoServices (Svtx08f2_CountryCodeRegionDaoServices value){ this.svtx08f2_CountryCodeRegionDaoServices = value; }
	public Svtx08f2_CountryCodeRegionDaoServices getSvtx08f2_CountryCodeRegionDaoServices(){ return this.svtx08f2_CountryCodeRegionDaoServices; }
	
	
}

