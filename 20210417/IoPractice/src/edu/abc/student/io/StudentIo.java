package edu.abc.student.io;

import java.io.File;
import java.io.IOException;

import edu.abc.io.FileUtils;
import edu.abc.student.Gender;
import edu.abc.student.Student;

public class StudentIo {

	public static final String PATH_STUDENT_DATA = "./resources/student";
	private static final String EXTN_OBJ_FILE = ".obj";

	private static String getFilePath(String fileName) throws IOException {
		if (fileName == null || fileName.isBlank()) {
			throw new IOException("Invalid File Name");
		}
		return  PATH_STUDENT_DATA+File.separator +fileName+ EXTN_OBJ_FILE;
	}

	public static void saveStudent(Student student) throws IOException {
		String path =  getFilePath(student.getFirstName());
		FileUtils.writeObject(student, path);
	}

	public static Student getStudent(String studentFirsName) throws IOException {
		String path =  getFilePath(studentFirsName);
		return (Student)FileUtils.readObject(path);
	}

	public static void main(String[] args) {
		Student student = new Student();
		student.setFirstName("Bob");
		student.setLastName("alice");
		student.setGender(Gender.MALE);
		//Ignore date for now
		//student.setDob(new Date(2001, 1, 1));
		try {
			saveStudent(student);
		} catch (IOException e) {
			System.err.println("Student save failed.");
		}

		Student studentR;
		String firstName = "Bob";
		try {
			studentR = getStudent(firstName);
			System.out.println(studentR);	 
		} catch (IOException e) {
			System.err.println("Student retrieval is failed: " + firstName);
		}
	}
}
