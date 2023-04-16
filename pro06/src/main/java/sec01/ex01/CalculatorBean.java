package sec01.ex01;
//°è»ê±âpro06-useBean
public class CalculatorBean {
	private int n1;
	private int n2;
	private String op;
	private int result;
	
	public CalculatorBean(){
		
	}
	
	public CalculatorBean(int n1, int n2, String op){
		this.n1 = n1;
		this.n2 = n2;
		this.op = op;
	}
	
	public int getN1() {
		return n1;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public void setN1(int n1) {
		this.n1 = n1;
	}
	public int getN2() {
		return n2;
	}
	public void setN2(int n2) {
		this.n2 = n2;
	}
	public String getOp() {
		return op;
	} 
	public void setOp(String op) {
		this.op = op;
	}
	public int calc() {
		switch(op) {
		case "+": result= n1 + n2; break;
		case "-": result= n1 - n2; break;
		case "*": result= n1 * n2; break;
		case "/": result= n1 / n2; break;
		}
		return result;
	}
}
