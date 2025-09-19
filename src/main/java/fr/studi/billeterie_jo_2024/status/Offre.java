package fr.studi.billeterie_jo_2024.status;

public enum Offre {
	SOLO, DUO, FAMILIALE;

	public int nbPlaces() {
		switch (this) {
		case SOLO: {
			return 1;
		}
		case DUO: {
			return 2;
		}
		case FAMILIALE: {
			return 4;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this);
		}
	}
}
