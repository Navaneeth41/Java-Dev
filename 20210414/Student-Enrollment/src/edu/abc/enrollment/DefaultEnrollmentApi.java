package edu.abc.enrollment;

import java.util.Calendar;
import java.util.Date;

import edu.abc.course.Course;
import edu.abc.course.Season;
import edu.abc.course.Semister;
import edu.abc.exceptions.InvalidDataException;
import edu.abc.student.Gender;
import edu.abc.student.Student;

public class DefaultEnrollmentApi implements IEnrollmentApi {
	
	@Override
	public Student createStudent(String firstName, String lastName, Gender gender, Date dob) throws InvalidDataException {
		if (firstName == null  || gender == null || dob == null) {
			throw new InvalidDataException("Missing Student data.");
		}
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setDob(dob);
		student.setGender(gender);
		return student;
	}

	@Override
	public Course createCourse(String name, int credits, Season season) throws InvalidDataException {
		if (season == null) {
			throw new InvalidDataException("Invalid Season.");
		}
		Course course = new Course();
		course.setName(name);
		course.setCredits(credits);
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Semister semister = new Semister(currentYear, season);
		course.setSemister(semister);
		return course;
	}

	@Override
	public void enroll(Student student, Course course) throws InvalidDataException {
		if (student != null) {
			student.setCourse(course);
		}
	}
}


