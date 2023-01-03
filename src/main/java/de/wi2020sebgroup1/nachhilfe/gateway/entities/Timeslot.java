package de.wi2020sebgroup1.nachhilfe.gateway.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Timeslot {

	@Id
	private String id;
	private String datum;
	private String wochentag;
	private String ort;
	private String fach;
	private String uhrzeit;
	private String dauer;
	private String preis;
	private String bezahlungErfolgt;
	private String schuelerId;
	private String lehrerId;
	private String distanz;
	
	public Timeslot(String id, String datum, String wochentag, String ort, String fach, String uhrzeit, String dauer,
			String preis, String bezahlungErfolgt, String schuelerId, String lehrerId, String distanz) {
		super();
		this.id = id;
		this.datum = datum;
		this.wochentag = wochentag;
		this.ort = ort;
		this.fach = fach;
		this.uhrzeit = uhrzeit;
		this.dauer = dauer;
		this.preis = preis;
		this.bezahlungErfolgt = bezahlungErfolgt;
		this.schuelerId = schuelerId;
		this.lehrerId = lehrerId;
		this.distanz = distanz;
	}

	public Timeslot(String datum, String wochentag, String ort, String fach, String uhrzeit, String dauer, String preis,
			String bezahlungErfolgt, String schuelerId, String lehrerId, String distanz) {
		super();
		this.datum = datum;
		this.wochentag = wochentag;
		this.ort = ort;
		this.fach = fach;
		this.uhrzeit = uhrzeit;
		this.dauer = dauer;
		this.preis = preis;
		this.bezahlungErfolgt = bezahlungErfolgt;
		this.schuelerId = schuelerId;
		this.lehrerId = lehrerId;
		this.distanz = distanz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getWochentag() {
		return wochentag;
	}

	public void setWochentag(String wochentag) {
		this.wochentag = wochentag;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getFach() {
		return fach;
	}

	public void setFach(String fach) {
		this.fach = fach;
	}

	public String getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(String uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public String getDauer() {
		return dauer;
	}

	public void setDauer(String dauer) {
		this.dauer = dauer;
	}

	public String getPreis() {
		return preis;
	}

	public void setPreis(String preis) {
		this.preis = preis;
	}

	public String getBezahlungErfolgt() {
		return bezahlungErfolgt;
	}

	public void setBezahlungErfolgt(String bezahlungErfolgt) {
		this.bezahlungErfolgt = bezahlungErfolgt;
	}

	public String getSchuelerId() {
		return schuelerId;
	}

	public void setSchuelerId(String schuelerId) {
		this.schuelerId = schuelerId;
	}

	public String getLehrerId() {
		return lehrerId;
	}

	public void setLehrerId(String lehrerId) {
		this.lehrerId = lehrerId;
	}

	public String getDistanz() {
		return distanz;
	}

	public void setDistanz(String distanz) {
		this.distanz = distanz;
	}

	@Override
	public String toString() {
		return "Timeslot [id=" + id + ", datum=" + datum + ", wochentag=" + wochentag + ", ort=" + ort + ", fach="
				+ fach + ", uhrzeit=" + uhrzeit + ", dauer=" + dauer + ", preis=" + preis + ", bezahlungErfolgt="
				+ bezahlungErfolgt + ", schuelerId=" + schuelerId + ", lehrerId=" + lehrerId + ", distanz=" + distanz
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bezahlungErfolgt, datum, dauer, distanz, fach, id, lehrerId, ort, preis, schuelerId,
				uhrzeit, wochentag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeslot other = (Timeslot) obj;
		return Objects.equals(bezahlungErfolgt, other.bezahlungErfolgt) && Objects.equals(datum, other.datum)
				&& Objects.equals(dauer, other.dauer) && Objects.equals(distanz, other.distanz)
				&& Objects.equals(fach, other.fach) && Objects.equals(id, other.id)
				&& Objects.equals(lehrerId, other.lehrerId) && Objects.equals(ort, other.ort)
				&& Objects.equals(preis, other.preis) && Objects.equals(schuelerId, other.schuelerId)
				&& Objects.equals(uhrzeit, other.uhrzeit) && Objects.equals(wochentag, other.wochentag);
	}

}
