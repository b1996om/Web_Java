package exam_03;

public class StudentVO {
	String id;
	String name;
	String univ;
	String birth;
	String email;
	
	public StudentVO() {
		System.out.println("StudentVO °´Ã¼ »ý¼º");
	}
	
	public StudentVO(String name, String univ, String birth, String email) {
		this.name = name;
		this.univ = univ;
		this.birth = birth;
		this.email = email;
	}
	
	public StudentVO(String id, String name, String univ, String birth, String email) {
		this.id = id;
		this.name = name;
		this.univ = univ;
		this.birth = birth;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniv() {
		return univ;
	}

	public void setUniv(String univ) {
		this.univ = univ;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
