package com.bsc.util;

import java.io.File;

import org.apache.struts2.ServletActionContext;

/*
 * 报告生成路径
 * 
 * 
 */
public class ReportExportPath 
{
	/**
	 * 体重管理报告生成路径
	 */
	public final static String WEIGHTREPORTPATH = ServletActionContext.getRequest().getRealPath("/")+"jsp"+File.separator+"weight"+File.separator+"templates"+File.separator+"weightimages";
	
}
