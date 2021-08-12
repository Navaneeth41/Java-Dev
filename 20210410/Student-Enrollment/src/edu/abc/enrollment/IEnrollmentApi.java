package edu.abc.enrollment;

import java.util.Date;

import edu.abc.course.Course;
import edu.abc.course.Season;
import edu.abc.exceptions.InvalidDataException;
import edu.abc.student.Gender;
import edu.abc.student.Student;

public interface IEnrollmentApi {
	Student createStudent(String firstName, String lastName, Gender gender, Date dob);

	Course createCourse(String name, int credits, Season season);

	void enroll(Student student, Course course) throws InvalidDataException;
}
