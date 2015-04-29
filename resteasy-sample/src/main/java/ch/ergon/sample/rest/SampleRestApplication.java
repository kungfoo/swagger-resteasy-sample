package ch.ergon.sample.rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ch.ergon.sample.rest.resources.PetResource;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.wordnik.swagger.jaxrs.listing.ApiListingResource;

@ApplicationPath(SampleRestApplication.APPLICATION_PATH)
public class SampleRestApplication extends Application {

	public static final String APPLICATION_PATH = "/rest";

	private static ImmutableSet<Class<?>> RESOURCES = ImmutableSet.<Class<?>> of(
			PetResource.class,
			ApiListingResource.class
			);

	public static Set<String> RESOURCE_PACKAGES = FluentIterable.from(RESOURCES)
			.transform(packageName())
			.toSet();
	
	@Override
	public Set<Class<?>> getClasses() {
		return RESOURCES;
	}

	private static Function<Class<?>, String> packageName() {
		return new Function<Class<?>, String>() {
			@Override
			public String apply(Class<?> clazz) {
				return clazz.getPackage().getName();
			}
		};
	}
}
