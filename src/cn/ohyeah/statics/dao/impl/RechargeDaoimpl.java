package cn.ohyeah.statics.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.springframework.stereotype.Repository;
import cn.ohyeah.statics.dao.IRechargeDao;
import cn.ohyeah.statics.model.RechargeColumn;
import cn.ohyeah.statics.model.RechargeInfo;

@Repository("RechargeDao")
public class RechargeDaoimpl extends BaseDaoImpl implements IRechargeDao {

	@Override
	public Map<String, Integer> getSubscribeDetail(String startTime,
			String endTime) {
		String sql = "select CONVERT(varchar(100), time, 23) as t, productId, sum(amount) as sum from [SubscribeRecord] where (time >= '"
				+ startTime +"') and (time <='" + endTime + "') group by CONVERT(varchar(100), time, 23), productId order by CONVERT(varchar(100), time, 23) desc";
		Query query = getSession().createSQLQuery(sql).addEntity(RechargeInfo.class);
		//List list = query.list();
		ScrollableResults sr = query.scroll();
		List<RechargeInfo> list = new ArrayList<RechargeInfo>();
		RechargeInfo ri = null;
		while(sr.next()){
			System.out.println(sr.get());
			//ri.setT(sr.getString(0));
			//ri.setProductId(sr.getInteger(1));
			//ri.setSum(sr.getInteger(2));
			/*String t = sr.getString(0);
			String productId = sr.getString(1);
			String sum = sr.getString(2);
			System.out.println(productId);
			System.out.println(t);
			System.out.println(sum);*/
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<String> getTableRows(String startTime, String endTime) {
		String sql = "select CONVERT(varchar(100), time, 23) as t from [SubscribeRecord] where (time >= '"+
  			  startTime +"') and (time <='" + endTime + "') group by CONVERT(varchar(100), time, 23) order by t desc";
		Query query = getSession().createSQLQuery(sql);
		List list = query.list();
		for(int i=0;i<list.size();i++){
			System.out.println("充值时间："+list.get(i));
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, String> getTableColumns(String startTime, String endTime) {
		String sql = "select distinct productId, productName from SubscribeRecord where (time >= '"+
				startTime +"') and (time <='" + endTime + "') order by productId desc";
		Query query = getSession().createSQLQuery(sql).addEntity(RechargeColumn.class);
		List list = query.list();
		Map<String,String> columnsList = new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			RechargeColumn rc = (RechargeColumn) list.get(i);
			System.out.println("已充值游戏："+rc.getProductName());
			columnsList.put(String.valueOf(rc.getProductId()), rc.getProductName());
		}
		return columnsList;
	}

	@Override
	public Map<String, Integer> getAllSubscribeDetail(String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
