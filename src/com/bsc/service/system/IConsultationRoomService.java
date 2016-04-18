package com.bsc.service.system;

import com.bsc.bean.Consultationroom;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IConsultationRoomService {

	public ResultModel insert(Consultationroom room);

	public ResultModel delete(String roomId);

	public ResultModel update(Consultationroom room);

	public ResultModel getConsultationRoomById(String roomId);

	public ResultModel getConsultationRoomList(QueryTerms queryTerms);

	}
