package de.wi2020sebgroup1.nachhilfe.gateway.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stats {
	
	@Id
	private String id;
	private String userId;
	private String registerDate;
	private int learningPoints;
	private int teachingPoints;
	private int profilePoints;
	
	public Stats(String id, String userId, String registerDate, int learningPoints, int teachingPoints,
			int profilePoints) {
		super();
		this.id = id;
		this.userId = userId;
		this.registerDate = registerDate;
		this.learningPoints = learningPoints;
		this.teachingPoints = teachingPoints;
		this.profilePoints = profilePoints;
	}

	public Stats(String userId, String registerDate, int learningPoints, int teachingPoints, int profilePoints) {
		super();
		this.userId = userId;
		this.registerDate = registerDate;
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
	
	
	
	public Stats(int learningPoints, int teachingPoints, int profilePoints) {
		super();
		this.learningPoints = learningPoints;
		this.teachingPoints = teachingPoints;
		this.profilePoints = profilePoints;
	}

	public Stats() {
		
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
		return registerDate;
	}

	public void setRegistrationDate(String registerDate) {
		this.registerDate = registerDate;
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
		return "Stats [id=" + id + ", userId=" + userId + ", registerDate=" + registerDate + ", learningPoints="
				+ learningPoints + ", teachingPoints=" + teachingPoints + ", profilePoints=" + profilePoints + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, learningPoints, profilePoints, registerDate, teachingPoints, userId);
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
				&& profilePoints == other.profilePoints && Objects.equals(registerDate, other.registerDate)
				&& teachingPoints == other.teachingPoints && Objects.equals(userId, other.userId);
	}
	
}
