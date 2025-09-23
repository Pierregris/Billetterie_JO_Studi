package fr.studi.billeterie_jo_2024.status;

import java.util.List;

import fr.studi.billeterie_jo_2024.pojo.Reservation;

public class ResultatGetPanier {
	private List<Reservation> reservations;
	private Boolean suppression;

	public ResultatGetPanier(List<Reservation> reservations, Boolean suppression) {
		this.reservations = reservations;
		this.suppression = suppression;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Boolean getSuppression() {
		return suppression;
	}

	public void setSuppression(Boolean suppression) {
		this.suppression = suppression;
	}
}
