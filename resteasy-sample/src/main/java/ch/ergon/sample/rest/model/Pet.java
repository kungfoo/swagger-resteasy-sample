package ch.ergon.sample.rest.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class Pet {

	public enum Kind {
		DOG, CAT, UNKNOWN
	}

	private String name;
	private Kind kind;
	private int ageInYears;

	public Pet(String name, Kind kind, int ageInYears) {
		this.name = checkNotNull(name, "Pet name cannot be null.");
		this.kind = checkNotNull(kind, "Kind cannot be null.");
		checkState(ageInYears >= 0, "age in years cannot be negative.");
		this.ageInYears = ageInYears;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public int getAgeInYears() {
		return ageInYears;
	}

	public void setAgeInYears(int ageInYears) {
		this.ageInYears = ageInYears;
	}

}
