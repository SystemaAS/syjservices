package no.systema.jservices.tds.z.maintenance.tdsncts.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
import no.systema.jservices.tds.z.maintenance.tdsncts.controller.rules.SVX030R_FBRUKT_U;
import no.systema.jservices.tds.z.maintenance.tdsncts.controller.rules.SVX030R_U;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxghDao;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.entities.SvxhDao;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services.SvxghDaoServices;
import no.systema.jservices.tds.z.maintenance.tdsncts.model.dao.services.SvxhDaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Jul, 2021
 * 
 */

@Controller
public class TdsMaintResponseOutputterController_SVX030_FBRUKT {
	private static Logger logger = Logger.getLogger(TdsMaintResponseOutputterController_SVX030_FBRUKT.class.getName());
	
	/**
	 * 
	 * SVXH - NCTS Export Data file
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservices/syjsSVX030R_FBRUKT.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservices/syjsSVX030R_FBRUKT.do?user=OSCAR&thavd=...etc
	 * 
	 */
	@RequestMapping(value="syjsSVX030R_FBRUKT.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSVX030R_FBRUKT");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String id = request.getParameter("id");
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				SvxhDao dao = new SvxhDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
	            if( StringUtils.isNotEmpty(dao.getThavd())  &&  StringUtils.isNotEmpty(dao.getThtdn()) &&  StringUtils.isNotEmpty(dao.getThsg()) ){
	            	logger.warn(dao.getThavd() + "XX" + dao.getThtdn() + "XX" + dao.getThsg());
            		logger.warn("findById");
            		list = this.svxhDaoServices.findById(dao, dbErrorStackTrace);
	            	
				}else{
					if(StringUtils.isNotEmpty(id)) {
						logger.warn("getListReservedGuaranteeWithId (active guarantees)");
						list = this.svxhDaoServices.getListReservedGuaranteeWithId(id, dbErrorStackTrace);
					}else {
						logger.warn("getListReservedGuaranty (all)");
						list = this.svxhDaoServices.getListReservedGuarantee(dbErrorStackTrace);
					}
				}
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
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
	 * File: 	SVXH
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservices/syjsSVX030R_U_releaseGuarantee.do?user=OSCAR
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsSVX030R_U_releaseGuarantee.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSVX030R_U_releaseGuarantee");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SvxhDao dao = new SvxhDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SVX030R_FBRUKT_U rulerLord = new SVX030R_FBRUKT_U();
            //Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if(rulerLord.isValidInput(dao, userName)){
					logger.info("Before UPDATE ...");
					dmlRetval = this.svxhDaoServices.releaseGuarantee(dao, dbErrorStackTrace);
						
						
				}else{
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
				
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
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
		return sb.toString();
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 * 
	 * Update SVXGH
	 * @Example UPDATE: http://gw.systema.no:8080/syjservices/syjsSVX030R_U_adjustGuarantee.do?user=OSCAR&tggnr=...etc
	 */
	@RequestMapping(value="syjsSVX030R_U_adjustGuarantee.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U_adjust( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSVX030R_U_adjustGuarantee");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SvxghDao dao = new SvxghDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            
            //Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if(StringUtils.isNotEmpty(dao.getTggnr()) && StringUtils.isNotEmpty(dao.getTggblb()) ){
					logger.info("Before UPDATE ...");
					dmlRetval = this.svxghDaoServices.adjustBruktGuarantee(dao, dbErrorStackTrace);
						
				}else{
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
				
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
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
		return sb.toString();
	}
	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SvxhDaoServices svxhDaoServices;
	public void setSvxhDaoServices (SvxhDaoServices value){ this.svxhDaoServices = value; }
	public SvxhDaoServices getSvxhDaoServices(){ return this.svxhDaoServices; }
	
	@Autowired
	private SvxghDaoServices svxghDaoServices;
	public void setSvxghDaoServices (SvxghDaoServices value){ this.svxghDaoServices = value; }
	public SvxghDaoServices getSvxghDaoServices(){ return this.svxghDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

