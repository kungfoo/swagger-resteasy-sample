package ch.ergon.sample.rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ch.ergon.sample.rest.resources.PetResource;

import com.google.common.collect.ImmutableSet;

@ApplicationPath("/rest")
public class SampleRestApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		return ImmutableSet.<Class<?>> of(
				
				PetResource.class
				
				);
	}
}
