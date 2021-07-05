package chap01;

public class Greeter {
	public String format;
	
	public String greet(String guest) {
		return String.format(format, guest); // formatting 해서 return 해주는 기능의 method
	}
	
	public void setFormat(String format) { // format을 먼저 set한 후에 guest를 대입. 안그러면 null
		this.format = format;
	}
}
