package edu.abc.course;

import java.util.Set;

public class Course implements Cloneable{
	
	private String name;
	private int credits;
	private Semister semister;
	private Set<Subject> subjects;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Semister getSemister() {
		return semister;
	}

	public void setSemister(Semister semister) {
		this.semister = semister;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public Course getClone() {
		try {
			return (Course)this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
