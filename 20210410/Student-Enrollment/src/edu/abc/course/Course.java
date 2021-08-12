package edu.abc.course;

public class Course implements Cloneable{
	
	private String name;
	private int credits;
	private Semister semister;

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
