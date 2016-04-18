package com.bsc.action.rc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.management.loading.PrivateClassLoader;

import org.apache.struts2.ServletActionContext;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.bean.Dictionary;
import com.bsc.bean.Hospital;
import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.Hospitalsectionoffice;
import com.bsc.bean.Nation;
import com.bsc.bean.Patient;
import com.bsc.bean.Systemuser;
import com.bsc.service.rc.IConsultationService;
import com.bsc.service.rc.IPatientService;
import com.bsc.service.system.IDictService;
import com.bsc.service.system.IHospitalSectionOfficeService;
import com.bsc.service.system.IHospitalService;
import com.bsc.service.system.INationService;
import com.bsc.service.system.ISystemUserService;
import com.bsc.util.RcUtil;
import com.bsc.util.SystemUtil;
import com.bsc.util.db.Dao;
import com.googlecode.jsonplugin.annotations.JSON;
import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.opensymphony.xwork2.ActionContext;

/**
 * 会诊管理
 * 
 * @author 韩进城
 * 
 */
public class RcAction {

	private List<Dictionary> cardTypes; // 证件类型

	private List<Dictionary> sexType; // 性别类型

	private List<Dictionary> marrigeType; // 婚姻类别

	private List<Nation> nations; // 民族列表

	private List<Dictionary> consulationTypes; // 会诊类型 14

	private List<Dictionary> consulationModes; // 会诊方式13

	private List<Dictionary> illnessTypes; // 病情情况19

	private List<Dictionary> chargeTypes; // 费用类型15

	private List<Dictionary> rcstatus; // 费用状态16

	private Nation nation;

	private String msg;

	private Long maxCount; // 全部记录数

	private int pagesize = 25; // 每页显示记录数

	private int currentpage = 1; // 当前页

	private IDictService dictService; // 字典管理

	private INationService nationService; // 民族

	private IHospitalService hospitalService; // 医院管理

	private List<Hospital> hospitals; // 医院列表

	private IHospitalSectionOfficeService ihospitalSectionOfficeService; // 专科

	private List<Hospitalsectionoffice> officeList; // 科室列表

	private String id; // 通用 的id

	private Patient patient; // 病人信息

	private Consultation consultation; // 会诊信息

	private IPatientService patientService;

	private String hospid, officeid, name, cancelMsg; // 医院id和科室id

	private ISystemUserService systemuserservice; // 系统用户接口

	private List<Systemuser> systemusers;

	private ConsultationQuery qy;// 模糊查询的封装

	private List<Consultation> listConsultations; // 会诊列表

	private IConsultationService consultationService; // 会诊接口

	private List<Hospitalsectionoffice> listoffice; // 科室列表

	private String status;

	private Systemuser systemuser;

	/**
	 * 根据自己的医院查询选择出有权限通讯的医院信息
	 * 
	 * @return
	 */
	public String findThenHospatil() {

		Systemuser systemuser = SystemUtil.getSystemuser();

		if (SystemUtil.isAdmin()) {

			this.msg = "管理员不能创建会诊！";

			return "json";
		}

		String hospid = systemuser.getHospital().getId();

		List<Hospitalrelation> Hospitalrelationlist = this.hospitalService
				.findAllNotThen(hospid); // 查询除了自己的医院

		if (Hospitalrelationlist != null && !Hospitalrelationlist.isEmpty()) {

			this.hospitals = new ArrayList<Hospital>();

			for (Hospitalrelation ho : Hospitalrelationlist) {

				this.hospitals.add(ho.getReceiverHospital());
			}

			this.msg = "SUCCESS";
		} else {

			this.msg = "没有找到您要申请的医院";
		}

		return "json";
	}

	/**
	 * 根据医院id和科室id查询出所有的专家信息
	 * 
	 * @return
	 */
	public String findHospatilOfficeUsers() {
		setNull();

		this.systemusers = this.systemuserservice.findHospOfficeUsers(
				this.hospid, this.officeid, name);

		if (this.systemusers != null && this.systemusers.size() > 0) {

			this.msg = "SUCCESS";
		} else {

			this.msg = "没有找到相关联的专家信息！";
		}

		return "json";
	}

	/**
	 * 系统页面初始化
	 * 
	 * @return
	 */
	public String init() {

		this.cardTypes = this.dictService.getDicts("03");
		this.sexType = this.dictService.getDicts("01");
		this.marrigeType = this.dictService.getDicts("02");
		this.consulationTypes = this.dictService.getDicts("14");
		this.consulationModes = this.dictService.getDicts("13");
		this.illnessTypes = this.dictService.getDicts("19");
		this.chargeTypes = this.dictService.getDicts("15");
		this.nations = this.nationService.findAll();

		this.msg = "SUCCESS";

		return "json";
	}

	private void setNull() {

		this.cardTypes = null;
		this.sexType = null;
		this.marrigeType = null;
		this.consulationTypes = null;
		this.consulationModes = null;
		this.illnessTypes = null;
		this.chargeTypes = null;
		this.nations = null;
		this.hospitals = null;

	}

	/**
	 * 增加病人信息和会诊信息
	 * 
	 * @return
	 */
	public String onCreatePatientAndConsult() {
		boolean flag = false;
		if (this.id != null && !"".equals(this.id)) {
			// 取出病人信息
			this.patient = this.patientService.findById(this.id);
			// 从这里保存会诊信息

			if (this.consultation != null) {

				Systemuser sys = SystemUtil.getSystemuser();
				this.consultation.setPatient(this.patient); // 添加病人信息
				this.consultation.setCallerId(sys.getHospitalId()); // 添加申请医院ID
				this.consultation.setId(UUID.randomUUID().toString());
				this.consultation.setAddTime(new Date());
				this.consultation
						.setConsultationStatus(RcUtil.SENDRCSTATUS_SEND); // 提交会诊申请
				flag = this.patientService.onCreateConsult(consultation);
				ActionContext.getContext().getSession()
						.put("consultation", this.consultation); // 将会诊信息保存在session中
			}

		}

		this.msg = flag ? "SUCCESS" : "ERROR";

		return "json";
	}

	/**
	 * 查询所属科室
	 * 
	 * @return
	 */
	public String findOffice() {

		if (this.id != null && !"".equals(this.id)) {

			this.officeList = this.ihospitalSectionOfficeService
					.findByHospitalOffice(id);

			this.msg = "SUCCESS";
		} else {

			this.msg = "ERROR";
		}

		setNull();

		return "json";
	}

	/**
	 * 取得会诊列表
	 * 
	 * @return
	 */
	public String findConsList() {

		List<Consultation> temp = this.consultationService.findAll(currentpage,
				pagesize);
		this.maxCount = this.consultationService.getMaxCount();
		this.listConsultations = new ArrayList<Consultation>();
		for (Consultation c : temp) {

			if (c.getConsultationStatus() != null
					&& !"".equals(c.getConsultationStatus())) {

				c.setConsultationStatus(Dao.findOne(
						"SELECT name  FROM Dictionary WHERE typeId='16' AND code='"
								+ c.getConsultationStatus() + "'").toString());
			}
			revoisRealName(c);
			this.listConsultations.add(c);
		}
		this.msg = "SUCCESS";
		return "json";
	}

	/**
	 * 查询一条会诊记录
	 * 
	 * @return
	 */
	public String findByIdCon() {

		this.consultation = this.consultationService.findById(this.id);

		if (this.consultation != null) {
			// 申请医院
			// this.consultation.setCallerId(Dao.findOne("SELECT name FROM Hospital WHERE id='"+this.consultation.getCallerId()+"'").toString());
			// 会诊类型
			this.consultation.setConsulationType(Dao.findOne(
					"SELECT name FROM Dictionary WHERE typeId='14' AND code='"
							+ this.consultation.getConsulationType() + "'")
					.toString());
			// 方式
			this.consultation.setConsulationMode(Dao.findOne(
					"SELECT name FROM Dictionary WHERE typeId='13' AND code='"
							+ this.consultation.getConsulationMode() + "'")
					.toString());
			// 病情情况
			this.consultation.setConditions(Dao.findOne(
					"SELECT name FROM Dictionary WHERE typeId='19' AND code='"
							+ this.consultation.getConditions() + "'")
					.toString());
			// 费别
			if (this.consultation.getChargeType() == null
					|| "".equals(this.consultation.getChargeType()))
				this.consultation.setChargeType("");
			else
				this.consultation.setChargeType(Dao.findOne(
						"SELECT name FROM Dictionary WHERE typeId='15' AND code='"
								+ this.consultation.getChargeType() + "'")
						.toString());
			// 医学专科
			this.consultation.setSectionOfficeId(Dao.findOne(
					"SELECT name FROM Hospitalsectionoffice WHERE hospitalId='"
							+ this.consultation.getReceiverId() + "' AND id='"
							+ this.consultation.getSectionOfficeId() + "'")
					.toString());
			// 所选专家 专家
			if (this.consultation.getRequestDoctorId() != null
					&& !"".equals(this.consultation.getRequestDoctorId())) {
				this.consultation.setRequestDoctorId(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ this.consultation.getRequestDoctorId() + "'")
						.toString());
			}
			// 所选专家 专家1
			if (this.consultation.getRequestDoctorId2() != null
					&& !"".equals(this.consultation.getRequestDoctorId2())) {
				this.consultation.setRequestDoctorId2(Dao
						.findOne(
								"SELECT realName FROM Systemuser WHERE userId='"
										+ this.consultation
												.getRequestDoctorId2() + "'")
						.toString());
			}
			// 所选专家 专家2
			if (this.consultation.getRequestDoctorId3() != null
					&& !"".equals(this.consultation.getRequestDoctorId3())) {
				this.consultation.setRequestDoctorId3(Dao
						.findOne(
								"SELECT realName FROM Systemuser WHERE userId='"
										+ this.consultation
												.getRequestDoctorId3() + "'")
						.toString());
			}
			// 所选专家 专家3
			if (this.consultation.getRequestDoctorId4() != null
					&& !"".equals(this.consultation.getRequestDoctorId4())) {
				this.consultation.setRequestDoctorId4(Dao
						.findOne(
								"SELECT realName FROM Systemuser WHERE userId='"
										+ this.consultation
												.getRequestDoctorId4() + "'")
						.toString());
			}
			this.systemuser = Dao.findOne("FROM Systemuser WHERE userId='"
					+ this.consultation.getReportUser() + "'");
		}

		this.msg = "SUCCESS";

		if (this.status != null && "report".equals(this.status)) {

			return "report";
		} else {
			return "json";
		}
	}

	/**
	 * 加载医院
	 * 
	 * @return
	 */
	public String findMeHospital() {

		List<Hospitalrelation> hs = null;

		if (!SystemUtil.isAdmin()) {
			String sid = SystemUtil.getSystemuser().getHospitalId();
			hs = Dao.find("FROM Hospitalrelation WHERE id.callerId='" + sid
					+ "'");

		} else {
			this.hospitals = Dao.find("FROM Hospital");
		}
		if (hs != null) {
			this.hospitals = new ArrayList<Hospital>();
			for (Hospitalrelation h : hs) {

				this.hospitals.add(h.getReceiverHospital());

			}

		}
		this.rcstatus = Dao.find("FROM Dictionary WHERE typeId='16'");
		this.msg = "SUCCESS";
		return "json";
	}

	/**
	 * 查询科室
	 * 
	 * @return
	 */
	public String findMeOffice() {

		if (this.id != null && !"".equals(this.id)) {

			this.listoffice = Dao
					.find("FROM Hospitalsectionoffice WHERE hospital.id='"
							+ this.id + "'");
		}

		this.msg = "SUCCESS";
		return "json";
	}

	/**
	 * 模糊查询
	 * 
	 * @return
	 */
	public String findkwAll() {

		this.listConsultations = new ArrayList<Consultation>();
		if (isQyNull(this.qy)) {
			List<Consultation> temp = this.consultationService.findAll(
					currentpage, pagesize, qy);
			for (Consultation c : temp) {

				if (c.getConsultationStatus() != null
						&& !"".equals(c.getConsultationStatus())) {

					c.setConsultationStatus(Dao.findOne(
							"SELECT name  FROM Dictionary WHERE typeId='16' AND code='"
									+ c.getConsultationStatus() + "'")
							.toString());
				}
				revoisRealName(c);
				this.listConsultations.add(c);
			}
			this.maxCount = this.consultationService.getMaxCount(qy);
		} else {
			return this.findConsList();
		}

		this.msg = "SUCCESS";
		return "json";
	}

	private boolean isQyNull(ConsultationQuery qy) {
		boolean flag = false;
		if (qy != null) {
			if (!"".equals(qy.getName()) && null != qy.getName()) {
				flag = true;
			} else if (!"".equals(qy.getEnd_date()) && null != qy.getEnd_date()) {
				flag = true;
			} else if (!"".equals(qy.getOffice()) && null != qy.getOffice()) {
				flag = true;
			} else if (!"".equals(qy.getReceiverHospital())
					&& null != qy.getReceiverHospital()) {
				flag = true;
			} else if (!"".equals(qy.getStart_date())
					&& qy.getStart_date() != null) {
				flag = true;
			} else if (!"".equals(qy.getStatus()) && null != qy.getStatus()) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 取消会诊
	 * 
	 * @return
	 */
	public String cancelCon() {

		if (this.id != null && !"".equals(this.id)) {

			Consultation con = Dao.findOne("FROM Consultation WHERE id='"
					+ this.id + "'");

			con.setConsultationStatus(RcUtil.SENDRCSTATUS_RCOVER);
			if (this.cancelMsg != null && !"".equals(this.cancelMsg)) {
				con.setCancelConResult(this.cancelMsg);
			}
			con.setUpdateTime(new Date());
			Dao.update(con);
			this.msg = "SUCCESS";
		} else {
			this.msg = "ERROR";
		}

		return "json";
	}

	public static void revoisRealName(Consultation c) {

		if (c != null) {

			if (c.getRequestDoctorId() != null
					&& !"".equals(c.getRequestDoctorId())) {
				c.setRequestDoctorId(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getRequestDoctorId() + "'").toString());
			}
			if (c.getRequestDoctorId2() != null
					&& !"".equals(c.getRequestDoctorId2())) {
				c.setRequestDoctorId2(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getRequestDoctorId2() + "'").toString());
			}
			if (c.getRequestDoctorId3() != null
					&& !"".equals(c.getRequestDoctorId3())) {
				c.setRequestDoctorId3(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getRequestDoctorId3() + "'").toString());
			}
			if (c.getRequestDoctorId4() != null
					&& !"".equals(c.getRequestDoctorId4())) {
				c.setRequestDoctorId4(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getRequestDoctorId4() + "'").toString());
			}

			if (c.getActualDoctorId() != null
					&& !"".equals(c.getActualDoctorId())) {
				c.setActualDoctorId(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getActualDoctorId() + "'").toString());
			}
			if (c.getActualDoctorId2() != null
					&& !"".equals(c.getActualDoctorId2())) {
				c.setActualDoctorId2(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getActualDoctorId2() + "'").toString());
			}
			if (c.getActualDoctorId3() != null
					&& !"".equals(c.getActualDoctorId3())) {
				c.setActualDoctorId3(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getActualDoctorId3() + "'").toString());
			}
			if (c.getActualDoctorId4() != null
					&& !"".equals(c.getActualDoctorId4())) {
				c.setActualDoctorId4(Dao.findOne(
						"SELECT realName FROM Systemuser WHERE userId='"
								+ c.getActualDoctorId4() + "'").toString());
			}

		}
	}

	// _start
	public Systemuser getSystemuser() {
		return systemuser;
	}

	public void setSystemuser(Systemuser systemuser) {
		this.systemuser = systemuser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCancelMsg() {
		return cancelMsg;
	}

	public void setCancelMsg(String cancelMsg) {
		this.cancelMsg = cancelMsg;
	}

	public List<Dictionary> getRcstatus() {
		return rcstatus;
	}

	public void setRcstatus(List<Dictionary> rcstatus) {
		this.rcstatus = rcstatus;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Systemuser> getSystemusers() {
		return systemusers;
	}

	public void setSystemusers(List<Systemuser> systemusers) {
		this.systemusers = systemusers;
	}

	@JSON(serialize = false)
	public ISystemUserService getSystemuserservice() {
		return systemuserservice;
	}

	public void setSystemuserservice(ISystemUserService systemuserservice) {
		this.systemuserservice = systemuserservice;
	}

	public String getHospid() {
		return hospid;
	}

	public void setHospid(String hospid) {
		this.hospid = hospid;
	}

	public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	@JSON(serialize = false)
	public IPatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(IPatientService patientService) {
		this.patientService = patientService;
	}

	public List<Hospitalsectionoffice> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<Hospitalsectionoffice> officeList) {
		this.officeList = officeList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JSON(serialize = false)
	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
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

	public List<Dictionary> getCardTypes() {
		return cardTypes;
	}

	public void setCardTypes(List<Dictionary> cardTypes) {
		this.cardTypes = cardTypes;
	}

	public List<Dictionary> getSexType() {
		return sexType;
	}

	public void setSexType(List<Dictionary> sexType) {
		this.sexType = sexType;
	}

	public List<Dictionary> getMarrigeType() {
		return marrigeType;
	}

	public void setMarrigeType(List<Dictionary> marrigeType) {
		this.marrigeType = marrigeType;
	}

	public List<Nation> getNations() {
		return nations;
	}

	public void setNations(List<Nation> nations) {
		this.nations = nations;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	@JSON(serialize = false)
	public INationService getNationService() {
		return nationService;
	}

	public void setNationService(INationService nationService) {
		this.nationService = nationService;
	}

	public List<Dictionary> getConsulationTypes() {
		return consulationTypes;
	}

	public void setConsulationTypes(List<Dictionary> consulationTypes) {
		this.consulationTypes = consulationTypes;
	}

	public List<Dictionary> getChargeTypes() {
		return chargeTypes;
	}

	public void setChargeTypes(List<Dictionary> chargeTypes) {
		this.chargeTypes = chargeTypes;
	}

	public List<Dictionary> getConsulationModes() {
		return consulationModes;
	}

	public void setConsulationModes(List<Dictionary> consulationModes) {
		this.consulationModes = consulationModes;
	}

	public List<Dictionary> getIllnessTypes() {
		return illnessTypes;
	}

	public void setIllnessTypes(List<Dictionary> illnessTypes) {
		this.illnessTypes = illnessTypes;
	}

	@JSON(serialize = false)
	public IHospitalService getHospitalService() {
		return hospitalService;
	}

	public void setHospitalService(IHospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	public List<Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

	public List<Consultation> getListConsultations() {
		return listConsultations;
	}

	public void setListConsultations(List<Consultation> listConsultations) {
		this.listConsultations = listConsultations;
	}

	@JSON(serialize = false)
	public IHospitalSectionOfficeService getIhospitalSectionOfficeService() {
		return ihospitalSectionOfficeService;
	}

	public void setIhospitalSectionOfficeService(
			IHospitalSectionOfficeService ihospitalSectionOfficeService) {
		this.ihospitalSectionOfficeService = ihospitalSectionOfficeService;
	}

	@JSON(serialize = false)
	public IConsultationService getConsultationService() {
		return consultationService;
	}

	public void setConsultationService(IConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	// _end
}
