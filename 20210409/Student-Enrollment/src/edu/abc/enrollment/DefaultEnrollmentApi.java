package edu.abc.enrollment;

import java.util.Calendar;
import java.util.Date;

import edu.abc.course.Course;
import edu.abc.course.Season;
import edu.abc.course.Semister;
import edu.abc.student.Gender;
import edu.abc.student.Student;

public class DefaultEnrollmentApi implements IEnrollmentApi {

	@Override
	public Student createStudent(String firstName, String lastName, Gender gender, Date dob) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setDob(dob);
		student.setGender(gender);
		return student;
	}

	@Override
	public Course createCourse(String name, int credits, Season season) {
		Course course = new Course();
		course.setName(name);
		course.setCredits(credits);
		Semister semister = new Semister(2021, season);
		course.setSemister(semister);
		return course;
	}

	@Override
	public void enroll(Student student, Course course) {
		if (student != null) {
			student.setCourse(course);
		}
	}
}
