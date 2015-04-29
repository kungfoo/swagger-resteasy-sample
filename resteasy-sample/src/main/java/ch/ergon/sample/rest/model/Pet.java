package ch.ergon.sample.rest.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.EnumSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;


public class Pet {
	
	public enum Action {
		CREATE, UPDATE, DELETE, COPY;
	}

	public enum Kind {
		DOG, CAT, UNKNOWN;
		
		public static final EnumSet<Kind> KNOWN_KINDS = EnumSet.complementOf(EnumSet.of(UNKNOWN));
	}

	private final String name;
	private final Kind kind;
	private int ageInYears;
	
	private final Set<Action> actions = ImmutableSet.of(Action.UPDATE, Action.COPY, Action.DELETE);

	public Pet(String name, Kind kind, int ageInYears) {
		this.name = checkNotNull(name, "Pet name cannot be null.");
		this.kind = checkNotNull(kind, "Kind cannot be null.");
		checkState(ageInYears >= 0, "age in years cannot be negative.");
		this.ageInYears = ageInYears;
	}

	public String getName() {
		return name;
	}

	public Kind getKind() {
		return kind;
	}


	public int getAgeInYears() {
		return ageInYears;
	}

	public Set<Action> getActions() {
		return actions;
	}
	
}
