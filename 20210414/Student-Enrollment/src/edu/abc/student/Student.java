package edu.abc.student;

import java.io.Serializable;
import java.util.Date;

import edu.abc.course.Course;
import edu.abc.exceptions.InvalidDataException;

public class Student implements Serializable {
	private static final long serialVersionUID = -7036284273448237036L;
	
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date dob;
	private Course course;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) throws InvalidDataException {
		if (course == null) {
			throw new InvalidDataException("Invalid Course");
		}
		this.course = course;
	}
	
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", dob=" + dob
				+ ", course=" + course + "]";
	}
}
