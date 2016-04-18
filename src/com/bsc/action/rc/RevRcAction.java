package com.bsc.action.rc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.bean.Consultationdigitalimage;
import com.bsc.bean.Dictionary;
import com.bsc.bean.Digitalimage;
import com.bsc.bean.Hospital;
import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.Hospitalsectionoffice;
import com.bsc.bean.Systemuser;
import com.bsc.dao.rc.IRevConsultationDao;
import com.bsc.service.rc.IConsultationService;
import com.bsc.service.rc.IRevConsultationService;
import com.bsc.util.RcUtil;
import com.bsc.util.SystemUtil;
import com.bsc.util.db.Dao;
import com.googlecode.jsonplugin.annotations.JSON;

/**
 * 接收action
 * @author 韩进城
 *
 */
public class RevRcAction {

	private Long maxCount; //全部记录数
	
	private int pagesize = 25;  //每页显示记录数
	
	private int currentpage=1; //当前页
	
	private List<Consultation> listConsultations;
	
	private IRevConsultationService revConsultationService; //接收服务接口
	
	private ConsultationQuery qy ;
	
	private String id,msg,doctorids;

	private List<Hospital> hospitals; //医院列表

	private List<Hospitalsectionoffice> listoffice;  //科室列表

	private Consultation consultation;  //会诊信息
	private IConsultationService consultationService;  //会诊接口

	private String status;
	private List<Dictionary> rcstatus; //费用状态16
	private List<Digitalimage> images; //影像集合
	private List<Systemuser> sysusers;  //专家列表
	private Date currDate=new Date();
	private String userId,password,diagnosis,opinion; //用户id，用户密码，初步诊断，诊断意见


	/**
	 * 加载接收记录
	 * @return
	 */
	public String findAll(){
		
		List<Consultation> temp = this.revConsultationService.findAll(currentpage, pagesize, qy);
		this.maxCount = this.revConsultationService.getMaxCount(qy);
		this.listConsultations = new ArrayList<Consultation>();
		for(Consultation c:temp){
			
			if(c.getConsultationStatus()!=null && !"".equals(c.getConsultationStatus())){
				
				c.setConsultationStatus(Dao.findOne("SELECT name  FROM Dictionary WHERE typeId='16' AND code='"+c.getConsultationStatus()+"'").toString());
			}
			RcAction.revoisRealName(c);
			this.listConsultations.add(c);
		}
		this.msg = "SUCCESS";
		return "json";
	}
	
	
	/**
	 * 加载医院
	 * @return
	 */
	public String findMeHospital(){
		
		List<Hospitalrelation> hs = null;
		
		if(!SystemUtil.isAdmin()){
			String sid = SystemUtil.getSystemuser().getHospitalId();
			hs = Dao.find("FROM Hospitalrelation WHERE id.receiverId='"+sid+"'");
			
		}else{
			this.hospitals = Dao.find("FROM Hospital");
		}
		if(hs!=null){
			this.hospitals = new ArrayList<Hospital>();
			for(Hospitalrelation h:hs){
				
				this.hospitals.add(h.getReceiverHospital());
				
			}
			
		}
		this.rcstatus = Dao.find("FROM Dictionary WHERE typeId='16'");
		this.msg = "SUCCESS";
		return "json";
	}
	
	/**
	 * 查询科室
	 * @return
	 */
	public String findMeOffice(){
		
		if(this.id!=null && !"".equals(this.id)){
			
			this.listoffice = Dao.find("FROM Hospitalsectionoffice WHERE hospital.id='"+this.id+"'");
		}
		
		this.msg = "SUCCESS";
		return "json";
	}
	
	/**
	 * 查询一条会诊记录
	 * @return
	 */
	public String findByIdCon(){
		
		this.consultation = this.consultationService.findById(this.id);
		
		if(this.consultation!=null){
			//申请医院
			//this.consultation.setCallerId(Dao.findOne("SELECT name FROM Hospital WHERE id='"+this.consultation.getCallerId()+"'").toString());
			//会诊类型
			this.consultation.setConsulationType(Dao.findOne("SELECT name FROM Dictionary WHERE typeId='14' AND code='"+this.consultation.getConsulationType()+"'").toString());
			//方式
			this.consultation.setConsulationMode(Dao.findOne("SELECT name FROM Dictionary WHERE typeId='13' AND code='"+this.consultation.getConsulationMode()+"'").toString());
			//病情情况
			this.consultation.setConditions(Dao.findOne("SELECT name FROM Dictionary WHERE typeId='19' AND code='"+this.consultation.getConditions()+"'").toString());
			//费别
			if(this.consultation.getChargeType() == null || "".equals(this.consultation.getChargeType()))
				this.consultation.setChargeType("");
			else
				this.consultation.setChargeType(Dao.findOne("SELECT name FROM Dictionary WHERE typeId='15' AND code='"+this.consultation.getChargeType()+"'").toString());
			//医学专科
			this.consultation.setSectionOfficeId(Dao.findOne("SELECT name FROM Hospitalsectionoffice WHERE hospitalId='"+this.consultation.getReceiverId()+"' AND id='"+this.consultation.getSectionOfficeId()+"'").toString());
			//所选专家 专家
			if(this.consultation.getRequestDoctorId()!=null && !"".equals(this.consultation.getRequestDoctorId())){
				this.consultation.setRequestDoctorId(Dao.findOne("SELECT realName FROM Systemuser WHERE userId='"+this.consultation.getRequestDoctorId()+"'").toString());
			}
			//所选专家 专家1
			if(this.consultation.getRequestDoctorId2()!=null && !"".equals(this.consultation.getRequestDoctorId2())){
				this.consultation.setRequestDoctorId2(Dao.findOne("SELECT realName FROM Systemuser WHERE userId='"+this.consultation.getRequestDoctorId2()+"'").toString());
			}
			//所选专家 专家2
			if(this.consultation.getRequestDoctorId3()!=null && !"".equals(this.consultation.getRequestDoctorId3())){
				this.consultation.setRequestDoctorId3(Dao.findOne("SELECT realName FROM Systemuser WHERE userId='"+this.consultation.getRequestDoctorId3()+"'").toString());
			}
			//所选专家 专家3
			if(this.consultation.getRequestDoctorId4()!=null && !"".equals(this.consultation.getRequestDoctorId4())){
				this.consultation.setRequestDoctorId4(Dao.findOne("SELECT realName FROM Systemuser WHERE userId='"+this.consultation.getRequestDoctorId4()+"'").toString());
			}
			//是否已婚
			if(this.consultation.getPatient().getMarriage()!= null && !"".equals(this.consultation.getPatient().getMarriage())){
				
				this.consultation.getPatient().setMarriage(Dao.findOne("SELECT name FROM Dictionary WHERE typeId='02' AND code='"+this.consultation.getPatient().getMarriage()+"'").toString());
			}
			//男女
			if(this.consultation.getPatient().getGender()!=null && !"".equals(this.consultation.getPatient().getGender())){
				
				this.consultation.getPatient().setGender(Dao.findOne("SELECT name FROM Dictionary WHERE typeId='01' AND code='"+this.consultation.getPatient().getGender()+"'").toString());
			}
			//民族
			if(this.consultation.getPatient().getNation()!=null && !"".equals(this.consultation.getPatient().getNation())){
				
				this.consultation.getPatient().setNation(Dao.findOne("SELECT name FROM Nation WHERE code="+this.consultation.getPatient().getNation()).toString());
			}
		}
		
		this.msg = "SUCCESS";
		
		if(this.status != null && "report".equals(this.status)){
			Consultation cc = this.consultationService.findById(this.id);
			cc.setBeginConTime(new Date());  //开始填写报告
			Dao.update(cc);
			this.consultation.setBeginConTime(cc.getBeginConTime());
			return "report";
		}else if(this.status == null && "".equals(this.status)){
			return "json";
		}else{
			return "info";
		}
	}
	/**
	 * 查看病历资料
	 * @return
	 */
	public String findImgs(){
		
		if(this.id!=null && !"".equals(this.id)){
			
			List<Consultationdigitalimage> list = Dao.find("FROM Consultationdigitalimage WHERE id.consultationId='"+this.id+"'");
			
			Iterator<Consultationdigitalimage> iterator = list.iterator();
			
			this.images = new ArrayList<Digitalimage>();
			while(iterator.hasNext()){
				
				Consultationdigitalimage conimg = iterator.next();
				
				this.images.add(conimg.getDigitalimage());
				
			}
			
		}
		return "dicoms";
	}
	
	
	/**
	 * 获得选择的医生信息
	 * @return
	 */
	public String findHosptialUser(){
		
		if(this.id != null && !"".equals(this.id)){
			
			Consultation csConsultation = Dao.findOne("FROM Consultation WHERE id='"+this.id+"'");
			
			this.sysusers = new ArrayList<Systemuser>();
			
			if(csConsultation.getRequestDoctorId()!= null && !"".equals(csConsultation.getRequestDoctorId())){
				Systemuser doc1=Dao.findOne("FROM Systemuser WHERE userId='"+csConsultation.getRequestDoctorId()+"'");
				this.sysusers.add(doc1);
			}
			if(csConsultation.getRequestDoctorId2()!= null && !"".equals(csConsultation.getRequestDoctorId2())){
				Systemuser doc2=Dao.findOne("FROM Systemuser WHERE userId='"+csConsultation.getRequestDoctorId2()+"'");
				this.sysusers.add(doc2);
			}
			if(csConsultation.getRequestDoctorId3()!= null && !"".equals(csConsultation.getRequestDoctorId3())){
				Systemuser doc3=Dao.findOne("FROM Systemuser WHERE userId='"+csConsultation.getRequestDoctorId3()+"'");
				this.sysusers.add(doc3);
			}
			if(csConsultation.getRequestDoctorId4()!= null && !"".equals(csConsultation.getRequestDoctorId4())){
				Systemuser doc4=Dao.findOne("FROM Systemuser WHERE userId='"+csConsultation.getRequestDoctorId4()+"'");
				this.sysusers.add(doc4);
			}
			this.msg = "SUCCESS";
		}else{
			this.msg = "ERROR";
		}
		
		return "json";
	}
	/**
	 * 保存已经安排的专家信息
	 * @return
	 */
	public String updateConsul(){
		
		if(this.id != null && !"".equals(this.id)){
			
			Consultation cc = Dao.findOne("FROM Consultation WHERE id='"+this.id+"'");
			
			if(this.doctorids!=null && !"".equals(this.doctorids)){
				
				String docs[] = this.doctorids.split("#");
				
				if(docs!= null && docs.length>0){
					
					for(int i=0;i<docs.length;i++){
						
						switch (i) {
						case 0:
							cc.setActualDoctorId(docs[i]);
							break;
						case 1:
							cc.setActualDoctorId2(docs[i]);
						case 2:
							cc.setActualDoctorId3(docs[i]);
						case 3:
							cc.setActualDoctorId4(docs[i]);
						default:
							break;
						}
						
					}
					
				}
			}
			
			cc.setUpdateTime(new Date());
			cc.setProcessRequestTime(new Date());
			cc.setConsultationStatus(RcUtil.SENDRCSTATUS_SUBMIT);
			Dao.update(cc);
			this.msg = "SUCCESS";
		}else{
			this.msg = "ERROR";
		}
		
		return "json";
	}
	/**
	 * 会诊申请不通过
	 * @return
	 */
	public String returnConsul(){
		
		if(this.id !=null &&!"".equals(this.id)){
			
			Consultation con = Dao.findOne("FROM Consultation WHERE id='"+this.id+"'");
			
			if(this.status != null && !"".equals(this.status)){
				
				con.setNotPassResult(status);
				con.setUpdateTime(new Date());
				con.setConsultationStatus(RcUtil.SENDRCSTATUS_ERROR);
				Dao.update(con);
				this.msg = "SUCCESS";
			}else{
				this.msg = "ERROR";
			}
		}
		
		return "json";
	}
	/**
	 * 验证用户信息
	 * @return
	 */
	public Systemuser valUser(){
		
		Systemuser flag = null;
		
		if(this.userId!=null && !"".equals(this.userId) && this.password!=null &&!"".equals(this.password)){
			
			Systemuser temp = Dao.findOne("FROM Systemuser WHERE userId='"+this.userId+"' AND password='"+this.password+"'");
			if(temp != null){
				flag = temp;
			}
		}
		
		return flag;
	}
	/**
	 * 保存会诊报告
	 * @return
	 */
	public String saveReport(){
		
		Systemuser sys = valUser();
		
		if(sys==null){
			
			this.msg="用户名或密码错误！";
			
		}else{
			if(this.id != null && !"".equals(this.id)){
				
				Consultation con = Dao.findOne("FROM Consultation WHERE id='"+this.id+"'");
				
				con.setRevDiag(this.diagnosis);
				con.setOpinion(this.opinion);
				con.setConsultationStatus(RcUtil.SENDRCSTATUS_SUBREPORT);
				con.setReportUser(sys.getUserId());
				con.setUpdateTime(new Date());
				Dao.update(con);
				
				this.msg = "SUCCESS";
			}else{
				this.msg = "ERROR";
			}
		}
		
		return "json";
	}
	
	
	
	//_start
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDiagnosis() {
		return diagnosis;
	}


	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}


	public String getOpinion() {
		return opinion;
	}


	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Date getCurrDate() {
		return currDate;
	}


	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}
	public String getDoctorids() {
		return doctorids;
	}


	public void setDoctorids(String doctorids) {
		this.doctorids = doctorids;
	}
	public List<Systemuser> getSysusers() {
		return sysusers;
	}


	public void setSysusers(List<Systemuser> sysusers) {
		this.sysusers = sysusers;
	}
	public List<Digitalimage> getImages() {
		return images;
	}


	public void setImages(List<Digitalimage> images) {
		this.images = images;
	}
	public List<Dictionary> getRcstatus() {
		return rcstatus;
	}


	public void setRcstatus(List<Dictionary> rcstatus) {
		this.rcstatus = rcstatus;
	}
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	@JSON(serialize=false)
	public IConsultationService getConsultationService() {
		return consultationService;
	}


	public void setConsultationService(IConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	public Consultation getConsultation() {
		return consultation;
	}


	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
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


	public List<Hospital> getHospitals() {
		return hospitals;
	}


	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}


	public List<Hospitalsectionoffice> getListoffice() {
		return listoffice;
	}


	public void setListoffice(List<Hospitalsectionoffice> listoffice) {
		this.listoffice = listoffice;
	}
	public ConsultationQuery getQy() {
		return qy;
	}

	public void setQy(ConsultationQuery qy) {
		this.qy = qy;
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



	public List<Consultation> getListConsultations() {
		return listConsultations;
	}


	public void setListConsultations(List<Consultation> listConsultations) {
		this.listConsultations = listConsultations;
	}


	@JSON(serialize=false)
	public IRevConsultationService getRevConsultationService() {
		return revConsultationService;
	}


	public void setRevConsultationService(
			IRevConsultationService revConsultationService) {
		this.revConsultationService = revConsultationService;
	}
	//_end
}
