package sec01.ex04;

public class test {
	
	String name = "ȫ�浿";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String test(String name) {
		String result;
		if(name.equals("ȫ�浿")) {
			System.out.println("����");
			result="����";
		} else {
			System.out.println("�л�");
			result="�л�";
		}
		return result;
	}
}

