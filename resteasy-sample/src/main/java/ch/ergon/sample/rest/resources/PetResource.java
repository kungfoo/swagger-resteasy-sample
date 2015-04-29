package ch.ergon.sample.rest.resources;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ch.ergon.sample.rest.resources.Pet.Kind;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

@Path("/pets")
@Produces("application/json")
@Consumes("application/json")
@LogExecutionTime
public class PetResource {
	
	
	private static Logger LOGGER = Logger.getLogger(PetResource.class.getSimpleName());
	private List<Pet> pets = createPets();
	
	@GET
    @Path("/")
	public PetListResult query(@BeanParam PetQuery query) {
		Predicate<Pet> predicate = query.toPredicate();
		LOGGER.info(String.format("Filtering with %s", predicate));
		ImmutableList<Pet> petsMatchingQuery = FluentIterable.from(pets).filter(predicate).toList();
		
		PetListResult result = new PetListResult();
		result.setNotifications(createNotifications());
		result.setPets(petsMatchingQuery);
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
