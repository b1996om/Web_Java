package sec01.ex04;

public class test {
	
	String name = "홍길동";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String test(String name) {
		String result;
		if(name.equals("홍길동")) {
			System.out.println("교수");
			result="교수";
		} else {
			System.out.println("학생");
			result="학생";
		}
		return result;
	}
}

