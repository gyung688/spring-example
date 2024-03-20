package sp5.chap02;

public class Greeter {

	private String format;
	
	public String greet(String guest) {
		return String.format(format, guest); // 새로운 문자열 생성
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
}
