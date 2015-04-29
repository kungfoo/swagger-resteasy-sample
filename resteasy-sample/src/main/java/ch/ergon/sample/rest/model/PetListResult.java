package ch.ergon.sample.rest.model;

import java.util.List;

public class PetListResult {
	private List<Pet> pets;
	private List<Notification> notifications;

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

}
