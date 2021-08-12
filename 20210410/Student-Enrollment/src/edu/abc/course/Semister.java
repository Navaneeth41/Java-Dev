package edu.abc.course;

public class Semister {
	private int year;
	private Season season;
	
	public Semister(int year, Season season) {
		super();
		this.year = year;
		this.season = season;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}
}
