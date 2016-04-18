package com.bsc.dao.rc.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.dao.rc.IConsultationDao;
import com.bsc.util.SystemUtil;

public class ConsultationDaoImpl extends HibernateDaoSupport implements
		IConsultationDao {

	public boolean onCreate(Consultation t) throws Exception {
		// TODO Auto-generated method stub

		this.getHibernateTemplate().save(t);

		return true;
	}

	public boolean onRemove(Consultation t) throws Exception {
		// TODO Auto-generated method stub

		this.getHibernateTemplate().delete(t);

		return true;
	}

	public boolean onUpdate(Consultation t) throws Exception {
		// TODO Auto-generated method stub

		this.getHibernateTemplate().update(t);

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Consultation> findAll(final int cp, final int ls)
			throws Exception {
		// TODO Auto-generated method stub

		List<Consultation> list = null;

		String hql = "FROM Consultation order by addTime ";

		if (!SystemUtil.isAdmin()) { // 如果是管理员查询全部

			hql = "FROM Consultation WHERE callerId = '"
					+ SystemUtil.getSystemuser().getHospitalId() + "'";
		}

		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((cp - 1) * ls);
		query.setMaxResults(ls);

		list = query.list();

		session.close();

		return list;
	}

	public Consultation findById(String id) throws Exception {
		// TODO Auto-generated method stub

		String hql = "FROM Consultation WHERE id = ?";

		List<Consultation> list = this.getHibernateTemplate().find(hql, id);

		if (!list.isEmpty()) {

			return list.get(0);
		}

		return null;
	}

	public Long getCount() throws Exception {
		// TODO Auto-generated method stub

		List<Long> list = this.getHibernateTemplate().find(
				"SELECT COUNT(id) FROM Consultation");

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return 0L;
	}

	/**
	 * 模糊查询
	 */
	public List<Consultation> findAll(int cp, int ls, ConsultationQuery qy)
			throws Exception {
		// TODO Auto-generated method stub

		List<Consultation> list = null;

		String hql = "FROM Consultation ";

		if (SystemUtil.isAdmin()) { // 如果是管理员查询全部

			String temp = retn_str(hql, qy, true);

			Session session = this.getSessionFactory().openSession();

			Query query = session.createQuery(temp);
			query.setFetchSize((cp - 1) * ls);
			query.setMaxResults(ls);

			list = query.list();

			session.close();

		} else {

			String temp = retn_str(hql, qy, false);

			Session session = this.getSessionFactory().openSession();

			Query query = session.createQuery(temp);
			query.setFetchSize((cp - 1) * ls);
			query.setMaxResults(ls);

			list = query.list();

			session.close();

		}

		return list;
	}

	private String retn_str(String hql, ConsultationQuery qy, boolean isAdmin) {

		StringBuffer sb = new StringBuffer(hql);

		if (!isAdmin && qy != null)
			sb.append(" WHERE callerId='")
					.append(SystemUtil.getSystemuser().getHospitalId())
					.append("' ");
		else if (qy != null && SystemUtil.isAdmin()) {
			sb.append(" WHERE ");

		} else if (!isAdmin && qy == null) {
			sb.append(" WHERE callerId='")
					.append(SystemUtil.getSystemuser().getHospitalId())
					.append("' ");
		}
		if (qy != null) {
			if (qy.getReceiverHospital() != null
					&& !"".equals(qy.getReceiverHospital())) {
				sb.append(" AND receiverId='")
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
	
	/**
	 * 模糊查询全部记录数
	 */
	public Long getCount(ConsultationQuery qy) throws Exception {
		// TODO Auto-generated method stub

		String hql = "SELECT COUNT(id) FROM Consultation ";

		hql = retn_str(hql, qy, SystemUtil.isAdmin());

		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery(hql);

		List<Long> list = query.list();

		session.close();

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return 0L;
	}

}
