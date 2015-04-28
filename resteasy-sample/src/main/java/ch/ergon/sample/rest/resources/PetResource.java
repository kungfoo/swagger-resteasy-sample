package ch.ergon.sample.rest.resources;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ch.ergon.sample.rest.resources.Pet.Kind;

@Path("/pets")
@Consumes("application/json")
@Produces("application/json")
@LogExecutionTime
public class PetResource {
	
	@GET
    @Path("/")
	public PetListResult query(PetQuery query) {
		PetListResult result = new PetListResult();
		result.setNotifications(createNotifications());
		result.setPets(createPets());
		return result;
	}

	private List<Pet> createPets() {
		Pet fido = new Pet();
		fido.setName("Fido");
		fido.setKind(Kind.DOG);
		fido.setAgeInYears(5);
		
		return newArrayList(fido );
	}

	private ArrayList<Notification> createNotifications() {
		Notification notification = new Notification();
		notification.setCode("OK");
		notification.setTranlatedMessage("Everything seems to be OK!");
		return newArrayList(notification);
	}
}
