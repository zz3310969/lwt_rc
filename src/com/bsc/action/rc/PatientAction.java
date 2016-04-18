package com.bsc.action.rc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Patient;
import com.bsc.bean.Systemuser;
import com.bsc.service.rc.IPatientService;
import com.bsc.service.system.IDictService;
import com.bsc.util.SystemUtil;
import com.googlecode.jsonplugin.annotations.JSON;

public class PatientAction {

	private String msg;
	
	private Long maxCount; //全部记录数
	
	private int pagesize = 25;  //每页显示记录数
	
	private int currentpage=1; //当前页
	
	private List<Patient> pagelist;
	
	private IPatientService patientservice;  // 病人管理
	
	private Patient patient;
	
	private String id;
	
	private List<Dictionary> cardTypes;  //证件类型
	
	private IDictService dictService;  //字典管理
	
	private String type,val;  //type证件类型，val 号码and姓名

	/**
	 * 初始化加载全部记录
	 * @return
	 */
	public String init(){
		
		this.pagelist = this.patientservice.findAll(currentpage, pagesize);  //分页查询所有
		
		this.maxCount = this.patientservice.getCount();  //取得全部记录数
		
		this.cardTypes = this.dictService.getDicts("03");  //初始化证件类型
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	
	/**
	 * 增加病人信息
	 * @return
	 */
	public String onCreatePatient(){
		
		boolean flag = false;
		
		if(this.patient != null){
			Systemuser systemuser = SystemUtil.getSystemuser();
			
			this.patient.setId(UUID.randomUUID().toString());
			this.patient.setAddTime(new Date());
			this.patient.setHospital(systemuser.getHospital());
			this.patient.setCreaterId(systemuser.getUserId());
			
			flag = this.patientservice.onCreatePatient(patient);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 删除病人信息
	 * @return
	 */
	public String onDeletePatient(){
		
		boolean flag = false;
		
		if(this.id != null && !"".equals(this.id)){
			
			Patient pp = this.patientservice.findById(id);
			
			flag = this.patientservice.onDeletePatient(pp);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 修改
	 * @return
	 */
	public String onUpdatePatient(){
		
		boolean flag =false;
		
		if(this.patient != null){
			
			Patient pp = this.patientservice.findById(this.patient.getId());
			
			Systemuser ss = SystemUtil.getSystemuser();
			
			this.patient.setUpdateTime(new Date());
			this.patient.setHospital(ss.getHospital());
			this.patient.setCreaterId(ss.getUserId());
			this.patient.setAddTime(pp.getAddTime());
			flag = this.patientservice.onUpdatePatient(patient);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	
	/**
	 * 查询一个对象
	 * @return
	 */
	public String findByidPatient(){
		
		if(this.id!=null && !"".equals(this.id)){
			
			this.patient = this.patientservice.findById(id);
			
		}
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	
	/**
	 * 模糊查询
	 * @return
	 */
	public String findAllKw(){
		
		this.pagelist = this.patientservice.findAll(currentpage, pagesize, type, val);
		
		this.maxCount = this.patientservice.getCount(type, val);
		
		this.msg = "SUCCESS";
		
		return  "json";
	}
	
	/**
	 * 判断当前的证件类型是否有此人
	 * @return
	 */
	public String checkUser(){
		
		if(this.id != null && !"".equals(this.id) && this.type != null && !"".equals(this.type)){
			
			this.patient = this.patientservice.findTypeAndIDPatient(id, type);
		}
		if(this.patient != null)
			this.msg = "SUCCESS";
		else
			this.msg = "该系统中不存在此病人信息，请单击确定后添加！";
		
		
		return "json";
	}
	//_start
	public List<Dictionary> getCardTypes() {
		return cardTypes;
	}

	public void setCardTypes(List<Dictionary> cardTypes) {
		this.cardTypes = cardTypes;
	}
	@JSON(serialize=false)
	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public List<Patient> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<Patient> pagelist) {
		this.pagelist = pagelist;
	}
	@JSON(serialize=false)
	public IPatientService getPatientservice() {
		return patientservice;
	}

	public void setPatientservice(IPatientService patientservice) {
		this.patientservice = patientservice;
	}
	//_end
}
