
public class HelloWorld {
	
	static int round(float num) {
		return (int)num;
	}
	
	static int ceil (float num) {
		//ceil comment
		return 0;
	}
	
	static String ageCategory(int age) {
		// ? :
		/*age = age < 0? -1: age;
		age = age >=0 && age <= 1? 1: age;
		age = age> 1 && age <= 18? 18: age;
		*/
		age = age < 0? -1: (age <= 1? 1: (age <= 18? 18: age));
		
		switch (age) { //expression
			case -1: // value
				return "invalid age.";
			case 1:
				return "baby";
			case 18:
				return "teen";
			default:
				return "adult";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ageCategory(-8));
	}
}
