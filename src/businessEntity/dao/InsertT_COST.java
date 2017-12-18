package businessEntity.dao;

import java.sql.SQLException;

import businessEntity.dto.T_COST;

public class InsertT_COST extends DaoConnectionDriverManeger{
	private static final String insertSql = "INSERT INTO T_COST values(?, ?, ?, ?)";

	public void insertTCost(T_COST tCost)  throws SQLException {
		try {
			ps = conn.prepareStatement(insertSql);
			ps.setString(1, tCost.COST_YMD);
			ps.setString(2, tCost.PRODUCT_CD);
			ps.setInt(3, tCost.COSTS);
			ps.setInt(4, tCost.COST_AMOUNT);

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

