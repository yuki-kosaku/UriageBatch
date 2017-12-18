package businessEntity.dao;

import java.sql.SQLException;

public class MergeT_STOCK extends DaoConnectionDriverManeger {
	private static final String insertSql = "merge into T_STOCK using"
			+ " (select * from T_COST where COST_YMD = (select SYS_BUSINESS_DAY from T_SYSTEM_INFO)) T_COST"
			+ " on (T_STOCK.PRODUCT_CD = T_COST.PRODUCT_CD)"
			+ " when matched then"
			+ " update set"
			+ " T_STOCK.STOCKS = T_STOCK.STOCKS + T_COST.COSTS"
			+ " when not matched then"
			+ " insert(T_STOCK.STOCK_YMD, T_STOCK.PRODUCT_CD, T_STOCK.STOCKS)"
			+ " values(T_COST.COST_YMD, T_COST.PRODUCT_CD, T_COST.COSTS)";

	public void mergeTStock() throws SQLException {
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
