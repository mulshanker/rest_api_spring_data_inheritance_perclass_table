package com.demo.web.rest.resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.web.JsonViews;
import com.demo.web.entity.Animal;
import com.demo.web.entity.BirdsAnimal;
import com.demo.web.entity.MammalsAnimal;
import com.demo.web.repository.AnimalRepository;
import com.demo.web.repository.BirdsAnimalRepository;
import com.demo.web.repository.MammalsAnimalRepository;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Service
@Path("/animals")
public class AnimalResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	private MammalsAnimalRepository mammalsAnimalRepository;

	@Autowired(required = true)
	private BirdsAnimalRepository birdsAnimalRepository;

	@Autowired(required = true)
	private AnimalRepository animalRepository;

	@Autowired(required = true)
	private ObjectMapper mapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() throws JsonGenerationException, JsonMappingException, IOException {

		logger.info("list()");

		ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = mapper.writerWithView(JsonViews.User.class);
		}
		List<Animal> allEntries = animalRepository.findAll();

		String valueAsString = viewWriter.writeValueAsString(allEntries);
		Response response = Response.status(Response.Status.OK).entity(valueAsString).build();

		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response read(@PathParam("id") Long id) {

		logger.info("read(id)");

		Animal animal = animalRepository.findOne(id);
		if (animal == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		Response response = Response.status(Response.Status.OK).entity(animal).build();

		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(JsonNode newEntry) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Response response = null;

		if(newEntry.get("type").getTextValue().equalsIgnoreCase(BirdsAnimal.class.getSimpleName())){
			//Create BirdsAnimal
			BirdsAnimal birdsAnimal = mapper.readValue(newEntry, BirdsAnimal.class);
			birdsAnimalRepository.save(mapper.readValue(newEntry, BirdsAnimal.class));
			response = Response.status(Response.Status.OK).entity(birdsAnimal).build();
		}else if(newEntry.get("type").getTextValue().equalsIgnoreCase(MammalsAnimal.class.getSimpleName())){
			//Create mammalsAnimal
			MammalsAnimal mammalsAnimal = mapper.readValue(newEntry, MammalsAnimal.class);
			mammalsAnimalRepository.save(mammalsAnimal);
			response = Response.status(Response.Status.OK).entity(mammalsAnimal).build();
		}
		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") Long id, JsonNode newEntry) throws JsonParseException, JsonMappingException, IOException {

		logger.info("update(): " + newEntry);


		Animal animal = animalRepository.findOne(id);
		if (animal == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		ObjectMapper mapper = new ObjectMapper();
		Response response = null;

		if(newEntry.get("type").getTextValue().equalsIgnoreCase(BirdsAnimal.class.getSimpleName())){
			//Create BirdsAnimal
			BirdsAnimal birdsAnimal = mapper.readValue(newEntry, BirdsAnimal.class);
			birdsAnimalRepository.save(mapper.readValue(newEntry, BirdsAnimal.class));
			response = Response.status(Response.Status.OK).entity(birdsAnimal).build();
		}else if(newEntry.get("type").getTextValue().equalsIgnoreCase(MammalsAnimal.class.getSimpleName())){
			//Create mammalsAnimal
			MammalsAnimal mammalsAnimal = mapper.readValue(newEntry, MammalsAnimal.class);
			mammalsAnimalRepository.save(mammalsAnimal);
			response = Response.status(Response.Status.OK).entity(mammalsAnimal).build();
		}

		return response;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {

		logger.info("delete(id)");

		animalRepository.delete(id);
		Response response = Response.status(Response.Status.OK).build();

		return response;
	}


/*
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
			boolean header = true;
			String[] headers = null;
			
			br = new BufferedReader(new InputStreamReader(uploadedInputStream));
			while ((line = br.readLine()) != null) {
	 
				if(header){
					headers = line.split(cvsSplitBy);
				}else{
					String[] data = line.split(cvsSplitBy);
					
					for (int i = 0; i < headers.length; i++) {
					
					}
				
				}
				
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("Done");
	  
	
		Response response = Response.status(Response.Status.OK).build();
		return response;

	}

*/
	private boolean isAdmin() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equalsIgnoreCase("anonymousUser")) {
			return false;
		}
		UserDetails userDetails = (UserDetails) principal;

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			if (authority.toString().equals("admin")) {
				return true;
			}
		}

		return false;
	}
}
