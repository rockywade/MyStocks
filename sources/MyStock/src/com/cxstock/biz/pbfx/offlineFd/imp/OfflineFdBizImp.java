package com.cxstock.biz.pbfx.offlineFd.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.biz.pbfx.offlineFd.OfflineFdBiz;
import com.cxstock.biz.pbfx.offlineFd.dto.OfflineFdDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.DatumInfo;
import com.cxstock.pojo.OfflineFd;
import com.cxstock.pojo.OfflineFdLog;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;
import com.cxstock.utils.system.UuidUtil;

/**
 * 线下辅导接口实现
 * @author root
 *
 */
public class OfflineFdBizImp implements OfflineFdBiz{
    
	
	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 线下辅导管理员 
	 * 列表查询
	 */
	@Override
	public void findPageOfflineFb(Page page, String[] property, String[] value,
			String bmId,String orderName1,String orderName2) {
		//查询记录表是否报过这个项目
		String hql = " from  OfflineFdLog where bmId = '"+bmId+"' and bmTag='1' ";
		List<OfflineFdLog>   list1 =  baseDao.findByHql(hql);
		//查询主表的数据
		List<OfflineFd> list = baseDao.findByProperty("OfflineFd",property,value,orderName1,orderName2,page.getStart(), page.getLimit());
		//接收数据（重组数据）
		List<OfflineFdDTO> list2 = new  ArrayList<OfflineFdDTO>();
		if(null !=list){
			for(int i=0;i<list.size();i++){
				//接收数据实体类
				OfflineFdDTO dto = new OfflineFdDTO();
			   if(list1.size()>0){
				for(int j = 0;j<list1.size();j++){
					//截取项目id
					//String xmid = list1.get(j).getXmid().substring(0,32); 
					if(list.get(i).getXmid().equals(list1.get(j).getXmid().substring(0,32))){
					    dto.setBmstatus("已报名");
					    break;
					}else{
						 dto.setBmstatus(list.get(i).getBmstatus());
					}
				 }
				}else{
				    dto.setBmstatus(list.get(i).getBmstatus());
				}
				dto.setFxxm(list.get(i).getFxxm());
				dto.setXmid(list.get(i).getXmid());
				if(i<10){
				   if(i == 0){
						dto.setXmxh("00"+1);
				   }else{
						dto.setXmxh("00"+(i+1));
				     }
				    }
				   if(10 <=i && i< 100){
						dto.setXmxh("0"+i);
				    }
				    if(i >= 100){
				    	dto.setXmxh(""+i);
				    }
				dto.setFxtime(list.get(i).getFxtime());
				dto.setFxaddress(list.get(i).getFxaddress());
				dto.setFxteacher(list.get(i).getFxteacher());
				dto.setTeachertel(list.get(i).getTeachertel());
				dto.setXmintro(list.get(i).getXmintro());
				dto.setXmzise(list.get(i).getXmzise());
				dto.setBmnumber(list.get(i).getBmnumber());
				dto.setStatus(list.get(i).getStatus());
				dto.setPlnumber(list.get(i).getPlnumber());
				dto.setTopTime(list.get(i).getTopTime());
				dto.setCreatetime(list.get(i).getCreatetime());
				dto.setCreaterid(list.get(i).getCreaterid());
				dto.setCreatername(list.get(i).getCreatername());
				list2.add(dto);
			}
		}
		
		int total = baseDao.count("OfflineFd",property,value);
		page.setRoot(list2);
		page.setTotal(total);
		
	}

	
	/**
	 * 线下辅导
	 * 新增数据保存
	 */
	@Override
	public Boolean saveOrUpdateOfflineFd(OfflineFd fb) {
		if(null == fb){
			return  false;
		}else{
			baseDao.saveOrUpdate(fb);
			return true;
		}
		
	}

	/**
	 *线下辅导
	 *活动数据删除
	 */
	@Override
	public void deleteOfflineFd(String xmid) {
		baseDao.deleteById(OfflineFd.class, xmid);
		
	}
	
	/**
	 * 批量刪除
	 */
	@Override
	public void deleteOfflinedAll(String offlineFdAll) {
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(offlineFdAll);
		for (int i = 0; i < jsonArray.size(); i++) {
			   JSONObject jo = (JSONObject) jsonArray.get(i);
			   baseDao.deleteById(OfflineFd.class, jo.getString("xmid"));
		}
	}
    
	/**
	 * 我的报名
	 * 线下辅导项目
	 */
	@Override
	public Boolean saveOrUpdate(OfflineFd fb) {
		if(null == fb ){
			return false;
		}else{
			baseDao.saveOrUpdate(fb);
			
			return true;
		}
	}
    
	/**
	 * 根据项目id去
	 * 查询线下辅导数据
	 */
	@Override
	public OfflineFdLog findSingle(String bmId,String xmid) {
		  if(null == xmid || "".equals(xmid)){
			  return null;
		    }
		  OfflineFdLog log =(OfflineFdLog) baseDao.loadObject("from OfflineFdLog where bmId = '"+bmId+"' and xmid like '"+xmid+"%'");
			 if(null != log){
				 return log;
			 }
		    return null;
	}
    
	
	
	
	/**
	 * 报名记录/
	 * 新增数据保存
	 */
	@Override
	public Boolean saveOrUpdateLog(OfflineFdLog log) {
		if(null == log){
			return false;
		}else{
			baseDao.saveOrUpdate(log);
			return true;
		}
	}

	/**
	 * 线下辅导报名更新/取消报名
	 */
	@Override
    public int updateOfflineFd(Integer bmnumber, String bmstatus,String xmid) {
		String hql = "UPDATE OfflineFd SET bmnumber = "+bmnumber+", bmstatus='"+bmstatus+"' WHERE xmid = '"+xmid+"'";
		Integer users = baseDao.update(hql);
		if(null !=  users){
			return users;
		}else{
			return 0;
		}
	}
      
	/**
	 * 评论的修改
	 */
	@Override
	public int updatePl(Integer plnumber, String xmid) {
		String hql = "UPDATE OfflineFd SET plnumber="+plnumber+" WHERE  xmid = '"+xmid+"'";
		Integer users = baseDao.update(hql);
		if(null !=  users){
			return users;
		}else{
			return 0;
		}
	}
    
	/**
	 * 我特别关注的项目
	 * 列表
	 */
	@Override
	public void findPageTbORCy(Page page, String[] property, String[] value,
			String orderName) {
		List<OfflineFdLog> list = baseDao.findByProperty1("OfflineFdLog",property,value,orderName,page.getStart(), page.getLimit());
		int total = baseDao.count("OfflineFdLog",property,value);
		//接收数据做处理
		List<OfflineFdLog> list1  =  new  ArrayList<OfflineFdLog>();
		 if(null !=list){
		    for(int i = 0;i<list.size();i++){
		    	OfflineFdLog log  =  new OfflineFdLog();
		    	 log.setBmId(list.get(i).getBmId());
				 log.setBmName(list.get(i).getBmName());
				 log.setDalei(list.get(i).getDalei());
				 log.setClasses(list.get(i).getClasses());
				 log.setPhone(list.get(i).getPhone());
				 log.setXmid(list.get(i).getXmid());
				 log.setXmmc(list.get(i).getXmmc());
				 log.setFxaddress(list.get(i).getFxaddress());
				 log.setBmtime(list.get(i).getBmtime());
				 log.setFxteacher(list.get(i).getFxteacher());
				 log.setXmzise(list.get(i).getXmzise());
				 log.setCreatername(list.get(i).getCreatername());
				 log.setBmnumber(list.get(i).getBmnumber());
				 if("1".equals(list.get(i).getBmTag())){
					 log.setBmstatus("已报名");
				 }else{
					log.setBmstatus(list.get(i).getBmstatus());
				 }
				 log.setPlnumber(list.get(i).getPlnumber());
				 if(i<10){
				 if(i == 0){
					   log.setXmxh("00"+1);
					}else{
					   log.setXmxh("00"+(i+1));
					}
				 }
				 if( i < 100 && i>10){
					 log.setXmxh("0"+i);
				 }
				 if(i>= 100){
					 log.setXmxh(""+i); 
				 }
				 log.setXmintro(list.get(i).getXmintro());
				 log.setBmTag(list.get(i).getBmTag());
				 log.setTbTag(list.get(i).getTbTag());
				 log.setPlnr(list.get(i).getPlnr());
				 log.setPlnrTime(list.get(i).getPlnrTime());
				 list1.add(log);
			}
		 }
		page.setRoot(list1);
		page.setTotal(total);
	}
   
	/**
	 * 项目管理
	 */
	@Override
	public void findMangeProject(Page page, String xmid,String orderName) {
		String hql ="from  OfflineFdLog where xmid like '"+xmid+"%'  and  bmTag = '1'  order by  " + orderName + " DESC ";
		List<OfflineFdLog> list = baseDao.findByHql(hql,page.getStart(),page.getLimit());
		List<OfflineFdLog>  list1= new ArrayList<OfflineFdLog>();
		if(null !=list){
			for(int i = 0;i<list.size();i++){
				OfflineFdLog log = new OfflineFdLog();
				
				  if(i<10){
					 if(i == 0){
						   log.setXmxh("0"+1);
						}else{
						   log.setXmxh("0"+(i+1));
						}
					 }
					 if(i>= 10){
						 log.setXmxh(""+i); 
					 }
				log.setBmId(list.get(i).getBmId());
				log.setBmName(list.get(i).getBmName());
				log.setDalei(list.get(i).getDalei());
				log.setClasses(list.get(i).getClasses());
				log.setPhone(list.get(i).getPhone());
				list1.add(log);
			}
		}
		
		String hql1 ="SELECT  count(*)  from OfflineFdLog where xmid like '"+xmid+"%' ";
		int total = baseDao.countQuery(hql1);
		page.setRoot(list1);
		page.setTotal(total);
	}
    
	/**
	 * 根据项目id
	 * 查询项目内容
	 */
	@Override
	public OfflineFd findSingOfflineFd(String xmid) {
		  if(null == xmid){
			  return null;
		  }else{
			  String  hql =  "from OfflineFd where  xmid = '"+xmid+"'";
			  OfflineFd  fd = (OfflineFd)baseDao.loadObject(hql);
			  if(null != fd){
				  return fd;
			  }
		  }
		return null;
	}
    
	/**
	 * 项目详情
	 */
	@Override
	public OfflineFdLog findSingleBy(String xmid) {
		 String  hql =  "from OfflineFdLog where  xmid = '"+xmid+"'";
		 OfflineFdLog  fd = (OfflineFdLog)baseDao.loadObject(hql);
		  if(null != fd){
			  return fd;
		  }else{
			  return null;
		  }
			  
	}
	
	
	/**
	 * 查询参与辅学项目
	 * 导出的数据
	 */
	@Override
	public List<OfflineFdLog> findAllOfflineFdLog(String xmid, String bmTag) {
		 String  hql = "from  OfflineFdLog where xmid like '"+xmid+"%' and  bmTag='"+bmTag+"'";
		 List<OfflineFdLog> list = baseDao.findByHql(hql);
		 if(null != list ){
			 return list;
		 }else{
		   return null;
		 }
		
	}

	/**
	 * 首页展示
	 * 的列表
	 */
	@Override
	public void findPageOfflineFd(Page page, String[] property, Object[] value) {
		String[] orderName = {"topTime","createtime"}; 
		String[] orderType = {"DESC","DESC"};
		List<OfflineFd> list = baseDao.findByProperty("OfflineFd",property,value,orderName,orderType, page.getStart(), page.getLimit());
		int total = baseDao.count("OfflineFd",property,value);
		List<OfflineFd> list1 = new ArrayList<OfflineFd>();
		if(null != list){
			for(int i=0;i<list.size();i++){
				OfflineFd qo =  new OfflineFd();
				qo.setXmid(list.get(i).getXmid());
				if(list.get(i).getFxxm().length()>10){
					qo.setFxxm(list.get(i).getFxxm().substring(0, 9)+"...");
				}else{
					qo.setFxxm(list.get(i).getFxxm());
				}
				qo.setFxtime(list.get(i).getFxtime());
				qo.setFxaddress(list.get(i).getFxaddress());
				qo.setFxteacher(list.get(i).getFxteacher());
				qo.setXmzise(list.get(i).getXmzise());
				qo.setBmnumber(list.get(i).getBmnumber());
				qo.setPlnumber(list.get(i).getPlnumber());
				qo.setXmintro(list.get(i).getXmintro());
				qo.setStatus(list.get(i).getStatus());
				list1.add(qo);
			}
		}
		
		page.setRoot(list1);
		page.setTotal(total);
		
	}
   
	/**
	 * 线下辅学
	 * 的批量操作
	 */
	@Override
	public void saveOrUpdaOfflineFdAll(OfflineFd pojo, String offlineFdAll) {
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(offlineFdAll);
		List<OfflineFd> dtList = new ArrayList<OfflineFd>();
		for (int i = 0; i < jsonArray.size(); i++) {
			   JSONObject jo = (JSONObject) jsonArray.get(i);
			   
			   OfflineFd fd = new OfflineFd();
				fd.setXmid(jo.getString("xmid"));
				fd.setXmxh(jo.getString("xmxh"));
				fd.setFxxm(jo.getString("fxxm"));
				fd.setFxtime(jo.getString("fxtime"));
				fd.setFxaddress(jo.getString("fxaddress"));
				fd.setFxteacher(jo.getString("fxteacher"));
				fd.setTeachertel(jo.getString("teachertel"));
				fd.setXmintro(jo.getString("xmintro"));
				fd.setXmzise(jo.getInt("xmzise"));
				fd.setBmnumber(jo.getInt("bmnumber"));
				fd.setBmstatus(jo.getString("bmstatus"));
				fd.setBz(jo.getString("bz"));
				fd.setPlnumber(jo.getInt("plnumber"));
				fd.setCreatetime(DateUtil.parse(jo.getString("createtime")));
				fd.setCreaterid(jo.getInt("createrid"));
				fd.setCreatername(jo.getString("creatername"));
				fd.setStatus(pojo.getStatus());
			   if(null == pojo.getTopTime()){
				   fd.setTopTime(DateUtil.parse(jo.getString("topTime"))); 
			   }else{
				   fd.setTopTime(pojo.getTopTime()); 
			   }
			   fd.setUpdaterid(pojo.getUpdaterid());
			   fd.setUpdatername(pojo.getUpdatername());
			   fd.setUpdatetime(new Date());
			   dtList.add(fd);
			
			}
			baseDao.saveOrUpdateAll(dtList);
	}

	/**
	 * 统计人气
	 */
	@Override
	public  int sumRenQi(String xmid) {
		 String[] property = { "xmid" , "bmTag" ,"tbTag"};
		 Object[] value = {xmid , '1','Y'};
		 int toatl = baseDao.count("OfflineFdLog", property, value);
	     if( toatl > 0 ){
	    		return toatl;
		    }else{
		    	
		    	return 0;
		    }
		  
	}

	

    
}
