package de.wi2020sebgroup1.nachhilfe.gateway.util;

import java.util.Objects;

public class Log {
	
	private String title;
	private String message;
	private String type;
	private String source;
	private String date;
	private String time;
	
	public Log(String title, String message, String type, String source, String date, String time) {
		super();
		this.title = title;
		this.message = message;
		this.type = type;
		this.source = source;
		this.date = date;
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Log [title=" + title + ", message=" + message + ", type=" + type + ", source=" + source + ", date="
				+ date + ", time=" + time + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, message, source, time, title, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		return Objects.equals(date, other.date) && Objects.equals(message, other.message)
				&& Objects.equals(source, other.source) && Objects.equals(time, other.time)
				&& Objects.equals(title, other.title) && Objects.equals(type, other.type);
	}

}
