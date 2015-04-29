package ch.ergon.sample.rest.resources;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.ws.rs.QueryParam;

import ch.ergon.sample.rest.model.Pet;
import ch.ergon.sample.rest.model.Pet.Kind;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class PetQuery {
	@QueryParam("maxAge")
	private Integer maxAgeInYears;

	@QueryParam("kind")
	private Kind kind;

	public Integer getMaxAgeInYears() {
		return maxAgeInYears;
	}

	public void setMaxAgeInYears(Integer maxAgeInYears) {
		this.maxAgeInYears = maxAgeInYears;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public Predicate<Pet> toPredicate() {
		return new PetPredicate(this);
	}

	public static class PetPredicate implements Predicate<Pet> {

		private final PetQuery query;

		public PetPredicate(PetQuery query) {
			this.query = checkNotNull(query);
		}

		@Override
		public boolean apply(Pet aPet) {
			return Predicates.and(agePredicate(), kindPredicate()).apply(aPet);
		}

		private Predicate<Pet> kindPredicate() {
			if (query.kind == null) {
				return Predicates.alwaysTrue();
			}
			return new Predicate<Pet>() {
				@Override
				public boolean apply(Pet pet) {
					return pet.getKind() == query.kind;
				}
			};
		}

		private Predicate<Pet> agePredicate() {
			if (query.maxAgeInYears == null) {
				return Predicates.alwaysTrue();
			}
			return new Predicate<Pet>() {
				@Override
				public boolean apply(Pet pet) {
					return pet.getAgeInYears() <= query.maxAgeInYears;
				}
			};
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.add("maxAge", query.maxAgeInYears)
					.add("kind", query.kind)
					.omitNullValues()
					.toString();
		}

	}
}
