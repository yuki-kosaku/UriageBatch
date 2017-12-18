package businessEntity.dao;

public enum ConnectionStrings {

	USER("CSC_USERS"), PASS("csc-users"), SERVER_NAME("localhost"), SID("XE");

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
