package business;


import java.io.FileNotFoundException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.BibleResult;
import beans.BibleResults;

/**
 * A REST service for searching the Bible
 * @author Josh Beck
 *
 */
@RequestScoped
@Path("/bible")
public class BibleRestService {
	
	@EJB
	BibleSearchInterface service;
	
	@GET
	@Path("/search/{phrase}")
	@Produces(MediaType.APPLICATION_JSON)
	public BibleResults getBibleFromWord(@PathParam("phrase") String phrase){
		//TODO: Handle error around phrase
		try {
			BibleResults r = service.searchBible(phrase);
			return r;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//TODO: Display 404 or 500 error
			return null;
		}
	}
}
