package no.systema.jservices.model.dao.services;
import java.util.*;
import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;

public interface Svtx08f2_CountryCodeRegionDaoServices {
	public List<GenericTableColumnsDao> getLandRecord(String landKode);
	public List<GenericTableColumnsDao> getLandRegionRecord(String landKode, String region);
	
}
