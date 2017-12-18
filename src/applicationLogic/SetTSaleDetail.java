package applicationLogic;

import java.util.List;

import businessEntity.dto.T_SALE_DETAIL;
import businesslogic.InsertData;
import businesslogic.SelectData;

public class SetTSaleDetail {

	public void setTSaleDetail() throws Exception {
		SelectData selectData = new SelectData();
		InsertData insertData = new InsertData();

		// 売上トランから売上データを取得する
		List<T_SALE_DETAIL> tSaleDetailDatas = selectData.getTSalesDetailData();

		for (T_SALE_DETAIL tSaleDetailData : tSaleDetailDatas) {
			insertData.insertTSaleDetail(tSaleDetailData);
		}
	}
}

