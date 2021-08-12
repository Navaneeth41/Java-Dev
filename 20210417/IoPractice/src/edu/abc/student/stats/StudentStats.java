package edu.abc.student.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.abc.student.Student;

public class StudentStats {

	private static List<Student> getRandomStudents(int size) {
		List<Student> studentSet = new ArrayList<>();
		final Random r = new Random();
		for (int i = 1; i <= size; i++) {
			Student s = new Student();
			s.setFirstName("Bob" + i);
			s.setLastName("Alice " + i);
			s.setGpa(r.nextInt(4));
			studentSet.add(s);
		}
		return studentSet;
	}

	public static void printStudentGpa(List<Student> students) {
		for (Student s : students) {
			System.out.println(s.getFirstName() + " " + s.getGpa());
		}
	}

	public static void printStudentName(List<Student> students) {
		for (Student s : students) {
			System.out.println(s.getFirstName() + " " + s.getLastName());
		}
	}

	public static Thread printStudentGpa1(final List<Student> students) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				printStudentGpa(students);
			}
		};
		Thread t = new Thread(r);
		t.start();
		return t;
	}

	public static Thread printStudentName1(final List<Student> students) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				printStudentName(students);
			}
		};

		Thread t = new Thread(r);
		t.start();
		return t;
	}

	public static void main(String[] args) {
		List<Student> students = getRandomStudents(10000);
		long timeIn = System.currentTimeMillis();
		printStudentGpa(students);
		printStudentName(students);
		long serialTime = System.currentTimeMillis() - timeIn;

		timeIn = System.currentTimeMillis();
		Thread t1 = printStudentGpa1(students);
		Thread t2 = printStudentName1(students);
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("===== printed time " + serialTime);
		System.out.println("===== printed time - concurrent " + (System.currentTimeMillis() - timeIn));
	}
}
