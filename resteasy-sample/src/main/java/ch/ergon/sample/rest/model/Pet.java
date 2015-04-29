package ch.ergon.sample.rest.model;

public class Pet {

	public enum Kind {
		DOG, CAT, UNKNOWN
	}

	private String name;
	private Kind kind;
	private int ageInYears;

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
