package edu.abc.course;

public class Subject implements Cloneable {
	private String name;
	private int credits;

	@Override
	public String toString() {
		return "Subject [name=" + name + ", credits=" + credits + "]";
	}

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

}
