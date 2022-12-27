package no.systema.jservices.tds.z.maintenance.felles.controller;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
/**
 * Container class for all the DTOs that return a json with a list
 * Since this DtoContainer is generic, there will be a price to pay further on when we marshall the JSON-Paylod
 * Refer to the specific mapper to see the extra implementation 
 * 
 * @author oscardelatorre
 * @date aug 2021
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericDtoContainer {
	
	private String user = null;
	private String errMsg = null;
	private List<Object> list = new ArrayList<Object>();
	private List<Object> oneorder = new ArrayList<Object>();
	private List<Object> orderList = new ArrayList<Object>();
	private List<Object> oneline = new ArrayList<Object>();
}
