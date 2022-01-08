package no.systema.jservices.tds.z.maintenance.felles.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
 
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.common.dao.services.SvtfiDaoService;
import no.systema.jservices.common.dao.SvtfiDao;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.tds.z.maintenance.felles.controller.rules.SVT055R_U;
//rules


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Maj 02, 2017
 * 
 */

@Controller
public class TdsMaintResponseOutputterController_SVT055 {
	private static Logger logger = LoggerFactory.getLogger(TdsMaintResponseOutputterController_SVT055.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		SVTFI
	 * 	 PGM:		SVT055R
	 * 	 Member: 	TDS Felles Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservices/syjsSVT055R.do?user=OSCAR&sv...
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservices/syjsSVT055R.do?user=OSCAR&sv...
	 * 
	 */
	@RequestMapping(value="syjsSVT055R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		String SQL_WILD_CARD = "%";
		JsonResponseWriter2<SvtfiDao> jsonWriter = new JsonResponseWriter2<SvtfiDao>();
		
		StringBuffer sb = new StringBuffer();
		List<SvtfiDao> list = null;
		try{
			logger.info("Inside syjsSVT055R");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				SvtfiDao dao = new SvtfiDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            
				//do SELECT
				logger.info("Before SELECT ...");
				list = this.svtfiDaoService.findAll(null);
				
				if (list != null){
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.findAll";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg );
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			}else{
				//write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			//write std.output error output
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
	 * Update Database DML operations
	 * File: 	SVTFI
	 * PGM:		SVT055
	 * Member: 	TDS Maintenance - UPDATE SPECIFIC
	 * Note: This method does not contain UPDATE. Only CREATE/DELETE. Ref. AS400 UC 
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservices/syjsSVT055R_U.do?user=OSCAR&mode=U/A/D&svt...
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSVT055R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSVT055R_U");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SvtfiDao dao = new SvtfiDao();
			SvtfiDao resultDao = new SvtfiDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SVT055R_U rulerLord = new SVT055R_U();
			//Key population in order to check if the record exists (for CREATE) and DELETE.
            Map params = new HashMap();
            //params.put("todo", dao.getTodo());
			
			//Start processing now
            if(userName!=null && !"".equals(userName)){
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						logger.info("Before DELETE ...");
						this.svtfiDaoService.deleteAll(params);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						List<SvtfiDao> list = new ArrayList<SvtfiDao>();
						//do ADD
						if("A".equals(mode)){
							list = this.svtfiDaoService.findAll(params);
							if(list!=null && list.size()>0){
								errMsg = "ERROR on CREATE/UPDATE: Record exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								logger.info("CREATE new ...");
								resultDao = this.svtfiDaoService.create(dao);
							}
						}else if("U".equals(mode)){
								logger.info("UPDATE ...");
								resultDao = this.svtfiDaoService.update(dao);
						}
						if(resultDao == null){
							errMsg = "ERROR on CREATE/UPDATE";
							status = "error";
							dbErrorStackTrace.append("Could not add/update dao=" + ReflectionToStringBuilder.toString(dao));
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						}else{
							// OK UPDATE
							sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
						}
					
				  }else{
						//write JSON error output
						errMsg = "ERROR on CREATE/UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				  }
				}
				
			}else{
				//write JSON error output
				errMsg = "ERROR on UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		logger.info(sb.toString());
		return sb.toString();
	}
	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("svtfiDaoService")
	private SvtfiDaoService svtfiDaoService;
	@Autowired
	@Required
	public void setSvtfiDaoService (SvtfiDaoService value){ this.svtfiDaoService = value; }
	public SvtfiDaoService getSvtfiDaoService(){ return this.svtfiDaoService; }
	
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

