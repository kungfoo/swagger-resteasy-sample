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
import javax.ws.rs.core.MediaType;

import ch.ergon.sample.rest.interceptors.LogExecutionTime;
import ch.ergon.sample.rest.model.Notification;
import ch.ergon.sample.rest.model.Pet;
import ch.ergon.sample.rest.model.Pet.Kind;
import ch.ergon.sample.rest.model.PetListResult;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pets")
@Api(
		value = "/pets",
		description = "Get some pets from a REST resoucce, eh?",
		produces = MediaType.APPLICATION_JSON,
		consumes = MediaType.APPLICATION_JSON)
@LogExecutionTime
public class PetResource {

	private static Logger LOGGER = Logger.getLogger(PetResource.class.getSimpleName());
	private List<Pet> pets = createPets();

	@GET
	@Path("/")
	@ApiOperation(value = "Get a list of pets matching a query.")
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
		return newArrayList(
				new Pet("Fido", Kind.DOG, 5),
				new Pet("Max", Kind.CAT, 3),
				new Pet("Max 2.0", Kind.CAT, 0),
				new Pet("Brookelyn the Gerbil", Kind.UNKNOWN, 2));
	}

	private ArrayList<Notification> createNotifications() {
		Notification notification = new Notification();
		notification.setCode("OK");
		notification.setTranlatedMessage("Everything seems to be OK!");
		return newArrayList(notification);
	}
}
