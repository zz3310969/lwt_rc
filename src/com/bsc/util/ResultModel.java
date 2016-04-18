package com.bsc.util;

public class ResultModel {

	// 对单条记录进行增删改（主要是删除和修改）操作时，记录的主键
	private String objectRecordId;
	// 操作是否成功
	private boolean isSuccess;
	// 操作最终的提示信息
	private String message;
	// 查询出的数据
	private Object data;
	// 操作返回代码，默认为零，没有赋值
	private int resultCode = 0;
	// 查询或批量删除时，符合查询条件的数据量
	private Long totalCount = new Long(0);

	public String getObjectRecordId() {
		return objectRecordId;
	}

	public void setObjectRecordId(String objectRecordId) {
		this.objectRecordId = objectRecordId;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		if (message != null)
			return message;
		if (resultCode < 0)
			return "操作出错！！";
		else
			return "操作成功！";

	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

}
