package edu.abc.enrollment;


import java.util.Date;

import edu.abc.course.Course;
import edu.abc.course.Season;
import edu.abc.course.Semister;
import edu.abc.exceptions.InvalidDataException;
import edu.abc.student.Gender;
import edu.abc.student.Student;

public class StudentEnrollmentApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/*
		Semister semister = new Semister(2021, Season.SUMMER);
		System.out.println("Semister "+ semister);
		
		Course course1 = new Course();
		course1.setName("Algorithms");
		course1.setCredits(4);
		course1.setSemister(semister);
		System.out.println("Course 1 " + course1 );
		
		Course course2 = new Course();
		course2.setName("Java");
		course2.setCredits(4);
		course2.setSemister(semister);
		System.out.println("Course 2 " + course2);
		
		Student student1 = new Student();
		student1.setFirstName("bob");
		student1.setLastName("job");
		student1.setDob(new Date(2000, 1, 1));
		student1.setGender(Gender.MALE);
		try {
			student1.setCourse(course2);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
		*/

		IEnrollmentApi enrollmentApi = new DefaultEnrollmentApi();
		Student student = enrollmentApi.createStudent("bob", "job",Gender.MALE,  new Date(2000, 1, 1));
		Course course = enrollmentApi.createCourse("Algorithms", 4, Season.SUMMER);
		try {
			enrollmentApi.enroll(student, null);
			System.out.println(" Enrollment is Successful. " + student.getCourse());
		}
		catch (InvalidDataException ex) {
			System.out.println("Enrollment is failed.");
			ex.printStackTrace();
		}
		
	}

}
