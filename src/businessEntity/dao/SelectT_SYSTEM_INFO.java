package businessEntity.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import businessEntity.dto.T_SYSTEM_INFO;

public class SelectT_SYSTEM_INFO extends DaoConnectionDriverManeger {

	private static final String selectAllSql = "select * from T_SYSTEM_INFO";

	public static List<T_SYSTEM_INFO> selectT_SYSTEM_INFO() {
		List<T_SYSTEM_INFO> getResult = new ArrayList<T_SYSTEM_INFO>();

		try {
			// Resultsetの作成
			ResultSet rset = stmt.executeQuery(selectAllSql);

			T_SYSTEM_INFO tSystemInfo = new T_SYSTEM_INFO();
			// 取得したデータを出力する
			while (rset.next()) {
				tSystemInfo = new T_SYSTEM_INFO();

				tSystemInfo.SYS_BUSINESS_DAY = rset.getString(1);

				getResult.add(tSystemInfo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getResult;
	}
}
