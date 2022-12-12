package de.wi2020sebgroup1.nachhilfe.gateway.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stats {
	
	@Id
	private String id;
	private String userId;
	private String registrationDate;
	private int learningPoints;
	private int teachingPoints;
	private int profilePoints;
	
	public Stats(String id, String userId, String registrationDate, int learningPoints, int teachingPoints,
			int profilePoints) {
		super();
		this.id = id;
		this.userId = userId;
		this.registrationDate = registrationDate;
		this.learningPoints = learningPoints;
		this.teachingPoints = teachingPoints;
		this.profilePoints = profilePoints;
	}

	public Stats(String userId, String registrationDate, int learningPoints, int teachingPoints, int profilePoints) {
		super();
		this.userId = userId;
		this.registrationDate = registrationDate;
		this.learningPoints = learningPoints;
		this.teachingPoints = teachingPoints;
		this.profilePoints = profilePoints;
	}

	public Stats(String userId, int learningPoints, int teachingPoints, int profilePoints) {
		super();
		this.userId = userId;
		this.learningPoints = learningPoints;
		this.teachingPoints = teachingPoints;
		this.profilePoints = profilePoints;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getLearningPoints() {
		return learningPoints;
	}

	public void setLearningPoints(int learningPoints) {
		this.learningPoints = learningPoints;
	}

	public int getTeachingPoints() {
		return teachingPoints;
	}

	public void setTeachingPoints(int teachingPoints) {
		this.teachingPoints = teachingPoints;
	}

	public int getProfilePoints() {
		return profilePoints;
	}

	public void setProfilePoints(int profilePoints) {
		this.profilePoints = profilePoints;
	}

	@Override
	public String toString() {
		return "Stats [id=" + id + ", userId=" + userId + ", registrationDate=" + registrationDate + ", learningPoints="
				+ learningPoints + ", teachingPoints=" + teachingPoints + ", profilePoints=" + profilePoints + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, learningPoints, profilePoints, registrationDate, teachingPoints, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stats other = (Stats) obj;
		return Objects.equals(id, other.id) && learningPoints == other.learningPoints
				&& profilePoints == other.profilePoints && Objects.equals(registrationDate, other.registrationDate)
				&& teachingPoints == other.teachingPoints && Objects.equals(userId, other.userId);
	}
	
}
