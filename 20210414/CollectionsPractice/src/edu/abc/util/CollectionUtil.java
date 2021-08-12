package edu.abc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import edu.abc.student.Student;

public class CollectionUtil {

	
	private static void listOperations(List data) {
		
		//Basic API
		System.out.println(data.size());
		//System.out.println(data.hashCode());
		System.out.println(data.toString());
		System.out.println(data.isEmpty());
		
		// Add
		data.add(10);
		data.add("A");
		data.add(0, 10000);
		data.add(1, new Student());
		System.out.println("data " + data);
		
		// Update
		   // for premitives
		data.remove(0);
		data.add(0, 9999);
		System.out.println("data " + data);
		
		   // for mutable objects
		((Student)data.get(1)).setFirstName("Bob");
		System.out.println("data " + data);

		// Search
		data.contains(10); //boolean

		
		// Iterate or manipulation
		// Iterator
		Iterator<Integer> dataIter = data.iterator();
		while(dataIter.hasNext()) {
			System.out.println(dataIter.next());
		}

		// for or while or do-while
		for (int i=0; i < data.size(); i++) {
		}

		//enhanced for loop
		for (Object item : data) {
		}
		// example 1
		List<Integer> sqreList = new ArrayList<>();
		for (Object dataItem: data) {
			if (dataItem.getClass() == Integer.class) {
				sqreList.add((Integer)dataItem * (Integer)dataItem);
			}
		}
		System.out.println(sqreList);
		
		//example 2
		data.stream().filter(item -> {	
			try {
				Integer	num = Integer.parseInt(item.toString());
				return num %2 == 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
				
		}).forEach(System.out::println);
		
		// Delete
		data.remove(0); // index
		System.out.println("data " + data);
		data.remove("A"); // by object
		System.out.println("data " + data);
		data.removeAll(data);
		System.out.println("data " + data);
	}
	
	private static void setOperations(HashSet data) {
		
		//Basic API
		System.out.println(data.size());
		System.out.println(data.hashCode());
		System.out.println(data.toString());
		System.out.println(data.isEmpty());
		
		// Add
		data.add(10);
		data.add("A");
		// data.add(0, 10000); // not applicable
		// data.add(1, new Student()); // not applicable
		System.out.println("data " + data);
		
		// Update
		   // for premitives
		// data.remove(0); // not applicable for index
		// data.add(0, 9999); // not applicable with position
		data.remove(10); // by object
		data.add(9999); // by object
		System.out.println("data " + data);
		
		   // for mutable objects
		// ((Student)data.get(1)).setFirstName("Bob"); // not applicable by index
		System.out.println("data " + data);

		// Search
		data.contains(10); //boolean

		
		// Iterate or manipulation
		// Iterator
		Iterator<Object> dataIter = data.iterator();
		while(dataIter.hasNext()) {
			System.out.println(dataIter.next());
		}

		// for or while or do-while
		for (int i=0; i < data.size(); i++) {
		}

		//enhanced for loop
		for (Object item : data) {
		}
		// example 1
		List<Integer> sqreList = new ArrayList<>();
		for (Object dataItem: data) {
			if (dataItem.getClass() == Integer.class) {
				sqreList.add((Integer)dataItem * (Integer)dataItem);
			}
		}
		System.out.println(sqreList);
		
		//example 2
		data.stream().filter(item -> {	
			try {
				Integer	num = Integer.parseInt(item.toString());
				return num %2 == 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
				
		}).forEach(System.out::println);
		
		// Delete
		data.remove(0); // not applicable by index
		System.out.println("data " + data);
		data.remove("A"); // by object
		System.out.println("data " + data);
		data.removeAll(data);
		System.out.println("data " + data);
	}
	
	private static void mapOperations(HashMap data) {
		
		//Basic API
		System.out.println(data.size());
		System.out.println(data.hashCode());
		System.out.println(data.toString());
		System.out.println(data.isEmpty());
		
		// Add
		data.put(4, 10);
		data.put(5, "A");
		data.put(10, new Student()); // not applicable
		System.out.println("data " + data);
		
		// Update
		// premitives will be converted to appropriate objects
		data.put(5, 9999); // by key-value pair
		System.out.println("data " + data);

		((Student)data.get(10)).setFirstName("Bob"); // not applicable by index
		System.out.println("data " + data);

		// Search
		data.containsKey(10); //boolean
		data.containsValue("A"); //boolean costly operation

		// Iterate or manipulation
		// Iterator
		Iterator<Object> dataIter = data.keySet().iterator(); // iterator by Key
		Iterator<Object> dataValueIter = data.values().iterator(); // iterator by values
		while(dataIter.hasNext()) {
			System.out.println(dataIter.next());
		}

		// for or while or do-while - not applicable by index

		//enhanced for loop
		for (Object entry : data.entrySet()) {
			System.out.println(entry + "class ... "+ entry.getClass());
		}
		// example 1
		Map<Object, Object> sqreMap = new HashMap<>();
		for (Object entryKey: data.keySet()) {
			Object value = data.get(entryKey);
			if (value.getClass() == Integer.class) {
				sqreMap.put(entryKey, (Integer)value * (Integer)value);
			}
		}
		System.out.println(sqreMap);
		
		//example 2
		data.entrySet().stream().filter(entry -> {	
			try {
				Object entryValue = ((Map.Entry<Object, Object>)entry).getValue();
				Integer	num = Integer.parseInt(entryValue.toString());
				return num %2 == 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
				
		}).forEach(System.out::println);
		
		// Delete
		 //not applicable by index or object
		data.remove(10); // by key
		System.out.println("data " + data);
		// removeAll() is not applicable
		data = new HashMap<>();
		System.out.println("data " + data);		
	}
	
	public static void main (String[] args) {
		// Number collection
		//int [] data = {10, 20, 30};
		// creation & save
		//List<Integer> data = List.of(10, 20, 30);
		//Set<Integer> data = new HashSet<>();
		//listOperations(new ArrayList(List.of(10, 20, 30)));
		//setOperations(new HashSet(Set.of(10, 20, 30)));
		mapOperations(new HashMap(Map.of(1, 10, 2, 20, 3, 30, 4, new Student())));
	}
}
