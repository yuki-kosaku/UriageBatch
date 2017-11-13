package businessEntity.dao;

import java.sql.SQLException;

public class InsertT_STOCK extends DaoConnectionDriverManeger {
	private static final String insertSql = "insert into T_STOCK select to_char(to_date(A.STOCK_YMD,'yyyy/mm/dd') + 1, 'yyyymmdd'), A.PRODUCT_CD, A.STOCKS from T_STOCK A where A.STOCK_YMD = (select to_char(to_date(B.SYS_BUSINESS_DAY,'yyyy/mm/dd') - 1, 'yyyymmdd') from T_SYSTEM_INFO B)";

	public void copyTStock() throws SQLException {
		try {
			ps = conn.prepareStatement(insertSql);

			//INSERT文を実行する
			int result = ps.executeUpdate();

			//処理件数を表示する
			System.out.println("結果：" + result);

			//コミット
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
	}
}