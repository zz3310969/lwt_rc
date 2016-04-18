package com.bsc.dao.rc.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.dao.rc.IRevConsultationDao;
import com.bsc.util.SystemUtil;

public class RevConsultationDAOImpl extends HibernateDaoSupport implements IRevConsultationDao {

	public List<Consultation> findAll(int cp, int ls, ConsultationQuery qy)
			throws Exception {
		// TODO Auto-generated method stub
		
		Session session = this.getSessionFactory().openSession();
		
		String hql = "FROM Consultation ";
		
		String temp = this.retn_str(hql, qy, SystemUtil.isAdmin())+"  order by updateTime";
		
		Query query = session.createQuery(temp);
		
		query.setFirstResult((cp-1)*ls);
		
		query.setMaxResults(ls);
		
		List<Consultation> list = query.list();
		
		session.close();
		
		return list;
	}

	public Long getMaxCount(ConsultationQuery qy) throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "SELECT COUNT(id) FROM Consultation ";
		
		String temp = this.retn_str(hql, qy, SystemUtil.isAdmin());
		
		Session session = this.getSessionFactory().openSession();
		
		Query query = session.createQuery(temp);
		
		List<Long> list = query.list();
		
		session.close();
		
		if(!list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}

	public Consultation findById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		Session session = this.getSessionFactory().openSession();
		
		Criteria cr = session.createCriteria(Consultation.class);
		
		cr.add(Restrictions.eq("id", id));
		
		List<Consultation> list = cr.list();
		
		if(!list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}

	
	private String retn_str(String hql, ConsultationQuery qy, boolean isAdmin) {

		StringBuffer sb = new StringBuffer(hql);

		if (!isAdmin && qy != null)
			sb.append(" WHERE receiverId='")
					.append(SystemUtil.getSystemuser().getHospitalId())
					.append("' ");
		else if (qy != null && SystemUtil.isAdmin()) {
			sb.append(" WHERE ");

		} else if (!isAdmin && qy == null) {
			sb.append(" WHERE receiverId='")
					.append(SystemUtil.getSystemuser().getHospitalId())
					.append("' ");
		}
		if (qy != null) {
			if (qy.getReceiverHospital() != null
					&& !"".equals(qy.getReceiverHospital())) {
				sb.append(" AND callerId='")
						.append(qy.getReceiverHospital()).append("' ");

			}

			if (qy.getName() != null && !"".equals(qy.getName())) {

				sb.append(" AND patient.name like '%").append(qy.getName())
						.append("%' ");
			}
			if (qy.getOffice() != null && !"".equals(qy.getOffice())) {

				sb.append(" AND sectionOfficeId='").append(qy.getOffice())
						.append("' ");

			}
			if (qy.getStatus() != null && !"".equals(qy.getStatus())) {

				sb.append(" AND consultationStatus='").append(qy.getStatus())
						.append("' ");
			}
			if (qy.getStart_date() != null && qy.getEnd_date() != null) {

				sb.append(" AND (requestTime between '")
						.append(new SimpleDateFormat("yyyy-MM-dd").format(qy.getStart_date())).append("' AND '")
						.append(new SimpleDateFormat("yyyy-MM-dd").format(qy.getEnd_date())).append("') ");

			} else {
				
				if (qy.getStart_date() != null) {

					sb.append(" AND requestTime >= '").append(new SimpleDateFormat("yyyy-MM-dd").format(qy.getStart_date()))
							.append("' ");
				} else if (qy.getEnd_date() != null) {

					sb.append(" AND requestTime <= '").append(new SimpleDateFormat("yyyy-MM-dd").format(qy.getEnd_date()))
							.append("' ");
				}
				
			}
		}

		return sb.toString();
	}
}
