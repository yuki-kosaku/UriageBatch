package businesslogic;

public enum ConnectionStrings {

	USER("CSC_USER"), PASS("CSC_USER"), SERVER_NAME("localhost"), SID("XE");

	 private String value;

	 //コンストラクタ
	 private ConnectionStrings(String value) {
	     this.value = value;
	  }

	 public String getValue()
	 {
		 return this.value;
	 }
};