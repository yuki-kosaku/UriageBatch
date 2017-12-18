package businessEntity.customDao;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import businessEntity.dao.DaoConnectionDriverManeger;
import businessEntity.dto.T_SALE;
import businessEntity.dto.T_SALE_DETAIL;

public class SelectT_SALES_TRAN extends DaoConnectionDriverManeger {

	private static final String selectTSalesFromTSalesTran = "select VOUCHER_NO, sum(SALE_AMOUNT) AS SALE_AMOUNT from (select A.VOUCHER_NO AS VOUCHER_NO, (A.SALES * B.SALE_UNIT_PRICE) AS SALE_AMOUNT from T_SALE_TRAN A, M_PRODUCT B WHERE A.PRODUCT_CD = B.PRODUCT_CD AND B.UNIT_PRICE_START_YMD = (select max(UNIT_PRICE_START_YMD) from M_PRODUCT C where C.PRODUCT_CD = B.PRODUCT_CD) and  A.INCLUSION_YMD = (select B.SYS_BUSINESS_DAY from T_SYSTEM_INFO B))group by VOUCHER_NO";
	private static final String selectTSalesDetailFromTSalesTran = "select A.VOUCHER_NO, A.PRODUCT_CD, sum(A.SALES) AS SALES from T_SALE_TRAN A WHERE A.INCLUSION_YMD = (select B.SYS_BUSINESS_DAY from T_SYSTEM_INFO B) group by A.VOUCHER_NO, A.PRODUCT_CD order by A.VOUCHER_NO";

	public List<T_SALE> selectTSalesFromTSalesTran() {
		List<T_SALE> getResult = new ArrayList<T_SALE>();

		try {
			// Resultsetの作成
			ResultSet rset = stmt.executeQuery(selectTSalesFromTSalesTran);

			// 取得したデータを出力する
			while (rset.next()) {
				T_SALE	tSale = new T_SALE();

				tSale.VOUCHER_NO = rset.getString(1);
				tSale.SHOP_CD = tSale.VOUCHER_NO.substring(0, 5);
				tSale.SALE_YMD = tSale.VOUCHER_NO.substring(6,13);
				tSale.SALE_AMOUNT = rset.getInt(2);

				getResult.add(tSale);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getResult;
	}

	public List<T_SALE_DETAIL> selectTSalesDetailFromTSalesTran() {
		List<T_SALE_DETAIL> getResult = new ArrayList<T_SALE_DETAIL>();

		try {
			// Resultsetの作成
			ResultSet rset = stmt.executeQuery(selectTSalesDetailFromTSalesTran);

			String voucherNo = "";
			int i = 1;

			// 取得したデータを出力する
			while (rset.next()) {
				T_SALE_DETAIL tSaleDetail = new T_SALE_DETAIL();

				// 伝票番号が変更した場合、カウンターを1に戻す
				if (!voucherNo.equals(rset.getString(1))) {
					voucherNo = rset.getString(1);
					i = 1;
				}

				tSaleDetail.VOUCHER_NO = rset.getString(1);
				tSaleDetail.DETAIL_NO = i;
				tSaleDetail.PRODUCT_CD = rset.getString(2);
				tSaleDetail.SALES = rset.getInt(3);

				// カウンターを1加算
				i++;

				getResult.add(tSaleDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getResult;
	}
}

