package com.bsc.action.rc;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import com.bsc.bean.Consultation;
import com.bsc.bean.Consultationdigitalimage;
import com.bsc.bean.ConsultationdigitalimageId;
import com.bsc.bean.Digitalimage;
import com.bsc.util.db.Dao;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.opensymphony.xwork2.ActionContext;


/**
 * 
 * @author 344761028@qq.com
 * @version 1.0
 */
public class Uploader
{

	public PageContext m_pc;
	String m_folder; 
	String m_curBasePath;  
	String m_filePathRel;  
	String m_fileName;  
	String m_userFilename; 
	boolean m_oriFilename;  
	public HttpServletRequest sr;

	public Uploader(PageContext pc, HttpServletRequest sr)
	{
		this.m_oriFilename = false;
		this.m_pc = pc;
		String path = sr.getContextPath();
		this.sr = sr;
		this.m_curBasePath = sr.getScheme() + "://" + sr.getServerName() + ":" + sr.getServerPort() + path + "/";
	}

	public void SetOriFileName(boolean b)
	{
		this.m_oriFilename = b;
	}

	public String GetFilePathRel()
	{
		return this.m_filePathRel + this.m_fileName;
	}
 
	public void CreateFolder()
	{
		Date timeCur = new Date();
		SimpleDateFormat fmtYY = new SimpleDateFormat("yyyy");
		SimpleDateFormat fmtMM = new SimpleDateFormat("MM");
		SimpleDateFormat fmtDD = new SimpleDateFormat("dd");
		String strYY = fmtYY.format(timeCur);
		String strMM = fmtMM.format(timeCur);
		String strDD = fmtDD.format(timeCur);
		Properties props = System.getProperties();  
		String separator = props.getProperty("file.separator");
		String patidString = this.sr.getParameter("patid");
		String pathRel = "upload/"+ patidString+"/"+ strYY+ strMM + strDD + "/";
		// String pathAbs = "upload\\" + strYY + "\\" + strMM + "\\" + strDD +
		// "\\";
		this.m_filePathRel = this.m_curBasePath + pathRel;
		
		String pathAbs = "upload"+ separator +patidString+separator+strYY+ strMM+ strDD + separator;
		this.m_folder = this.m_pc.getServletContext().getRealPath("/") + pathAbs;
		
		/*Properties properties = System.getProperties();
		String os = properties.getProperty("os.name");
		if(os.indexOf("Windows")!=-1){
			this.m_folder="E:"+separator+"dicom"+separator+patidString+separator+strYY+ strMM+ strDD + separator;
		}else{
			this.m_folder=separator+"usr"+separator+"dicom"+separator+patidString+separator + strYY+ strMM+ strDD + separator;
		}*/
		
		File f = new File(this.m_folder);
		if (!f.exists())
		{
			f.mkdirs();
		}
	}
	public String GenerateFileName()
	{
		Date timeCur = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("HHmmssSSSS");
		String timeStr = fmt.format(timeCur);
		return timeStr;
	}

	public void SaveFile(com.jspsmart.upload.File upFile) throws SmartUploadException, IOException
	{
		this.m_fileName = new String(upFile.getFileName().getBytes("GB18030"), "utf-8");
		if (!this.m_oriFilename)
		{

			if (null != this.m_userFilename && !this.m_userFilename.isEmpty())
			{
				//this.m_fileName = this.m_userFilename + "." + upFile.getFileExt();
				this.m_fileName = this.m_userFilename;
			} else
			{
				//this.m_fileName = this.GenerateFileName() + "." + upFile.getFileExt();
				this.m_fileName = this.GenerateFileName();
			}
		}

		this.CreateFolder();
		
		String filePath = this.m_folder + this.m_fileName;
		/////////////////////////////////////////////保存到数据库中
		String patidString = this.sr.getParameter("patid");
		String conid = this.sr.getParameter("conid");
		saveImg(filePath,patidString,conid);
		try
		{
			upFile.saveAs(filePath, SmartUpload.SAVE_PHYSICAL);
		} catch (SmartUploadException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public void setM_userFilename(String filename)
	{
		m_userFilename = filename;
	}
	/**
	 * 
	 * @param filename影像地址
	 * @param patid 病人id
	 * @param conid 会诊id
	 */
	public void saveImg(String filename,String patid,String conid){
		
		Digitalimage dm = new Digitalimage();
		dm.setAddTime(new Date());
		dm.setId(UUID.randomUUID().toString());
		dm.setPatientId(patid);
		//filename = filename.substring(0,filename.lastIndexOf(File.separator));
		dm.setUrl(filename);
		Dao.save(dm);  //将影像保存到数据库中
		
		ConsultationdigitalimageId cid = new ConsultationdigitalimageId();
		cid.setConsultationId(conid);
		cid.setDigitalImageId(dm.getId());
		Consultationdigitalimage cm = new Consultationdigitalimage();
		Consultation consultation = Dao.findOne("FROM Consultation where id='"+conid+"'");
		cm.setAddTime(new Date());
		cm.setConsultation(consultation);
		cm.setDigitalimage(dm);
		cm.setId(cid);
		Dao.save(cm);
	}
	
}
