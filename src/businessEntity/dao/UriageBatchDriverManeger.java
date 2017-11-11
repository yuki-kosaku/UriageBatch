package businessEntity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UriageBatchDriverManeger {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	@SuppressWarnings("finally")
	public Connection getConnection() {
		Connection conn = null;

		try {
			// JBBCドライバクラスのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connectionの作成
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + ConnectionStrings.SERVER_NAME.getValue() + ":1521:"
							+ ConnectionStrings.SID.getValue(),
					ConnectionStrings.USER.getValue(), ConnectionStrings.PASS.getValue());
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			return conn;

		}
	}
}
