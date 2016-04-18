package com.bsc.action.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.xml.registry.infomodel.User;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Linkmenu;
import com.bsc.bean.Role;
import com.bsc.bean.Roleright;
import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.bsc.service.system.impl.LinkMenuServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class LinkMenuManagement extends BaseAction {

	private static final long serialVersionUID = 1L;

	private LinkMenuServiceImpl linkMenuServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String primaryKey;
	private String textMenuId;
	private String textWebPageId;
	private String textareaDescription;
	private String textMenuName;
	private String textFunctionModuleId;
	private String selectModuleLevel;
	private String selectParentModuleId;
	private ResultModel result;

	public String insert() {
		Linkmenu menu = new Linkmenu();
		menu.setMenuId(UUID.randomUUID().toString());
		menu.setAddTime(new Date());
		menu.setUpdateTime(new Date());
		if (textareaDescription != null && !textareaDescription.isEmpty()) {
			menu.setDescription(textareaDescription);
		}
		if (textFunctionModuleId != null && !textFunctionModuleId.isEmpty()) {
			menu.setFunctionModuleId(textFunctionModuleId);
		}
		if (textMenuName != null && !textMenuName.isEmpty()) {
			menu.setMenuName(textMenuName);
		}
		if (textWebPageId != null && !textWebPageId.isEmpty()) {
			menu.setWebPageId(textWebPageId);
		}

		result = linkMenuServiceImpl.insert(menu);

		return "json";
	}

	public String delete() {
		result = linkMenuServiceImpl.delete(primaryKey);
		return "json";
	}

	public String update() {

		Linkmenu menu = new Linkmenu();
		menu.setUpdateTime(new Date());
		if (textMenuId != null && !textMenuId.isEmpty())
			menu.setMenuId(textMenuId);
		if (textareaDescription != null && !textareaDescription.isEmpty()) {
			menu.setDescription(textareaDescription);
		}
		if (textFunctionModuleId != null && !textFunctionModuleId.isEmpty()) {
			menu.setFunctionModuleId(textFunctionModuleId);
		}
		if (textMenuName != null && !textMenuName.isEmpty()) {
			menu.setMenuName(textMenuName);
		}
		if (textWebPageId != null && !textWebPageId.isEmpty()) {
			menu.setWebPageId(textWebPageId);
		}

		result = linkMenuServiceImpl.update(menu);

		return "json";
	}

	public String getModel() {
		result = linkMenuServiceImpl.getLinkMenuById(primaryKey);

		return "json";
	}

	public String queryRecord() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder linkMenuCriterionAndOrder = new CriterionAndOrder();
		linkMenuCriterionAndOrder.setCriteriaName(Linkmenu.class.getName());
		linkMenuCriterionAndOrder.setCriteriaAsName("LM");

		CriterionAndOrder functionModuleCriterionAndOrder = new CriterionAndOrder();
		functionModuleCriterionAndOrder.setCriteriaName("functionModuleRef");
		functionModuleCriterionAndOrder.setCriteriaAsName("FM");
		functionModuleCriterionAndOrder
				.setCriteriaSpecification(CriteriaSpecification.LEFT_JOIN);

		CriterionAndOrder webPageCriterionAndOrder = new CriterionAndOrder();
		webPageCriterionAndOrder.setCriteriaName("webpage");
		webPageCriterionAndOrder.setCriteriaAsName("WP");
		webPageCriterionAndOrder
				.setCriteriaSpecification(CriteriaSpecification.LEFT_JOIN);

		if (textMenuName != null && !textMenuName.trim().isEmpty())
			linkMenuCriterionAndOrder.getCriterionList().add(
					Restrictions.like("LM.menuName", "%" + textMenuName + "%"));

		if (selectModuleLevel != null && !selectModuleLevel.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("FM.moduleLevel", selectModuleLevel));
		if (selectParentModuleId != null
				&& !selectParentModuleId.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("FM.parentModuleId", selectParentModuleId));

		functionModuleCriterionAndOrder.getOrderList().add(
				Order.asc("FM.parentModuleId"));
		functionModuleCriterionAndOrder.getOrderList().add(
				Order.asc("FM.sortField"));

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("LM.menuName").as("menuName"));
		pList.add(Projections.property("LM.menuId").as("primaryKey"));
		pList.add(Projections.property("LM.addTime").as("addTime"));
		pList.add(Projections.property("FM.moduleLevelText").as("menuLevel"));
		// pList.add(Projections.property("FM.moduleName").as("functionModuleName"));
		pList.add(Projections.property("WP.url").as("pageUrl"));

		// 要查询成的属性
		queryTerms.setProjectionList(pList);
		// 要序列化成的实体类
		queryTerms.setPersistentClass(Linkmenu.class);

		queryTerms.getCriterionAndOrderList().add(linkMenuCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(
				functionModuleCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(webPageCriterionAndOrder);

		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);

		result = linkMenuServiceImpl.getLinkMenuList(queryTerms);

		return "json";
	}

	@SuppressWarnings("unchecked")
	public String getMenuListByRole2() {

		Systemuser user = (Systemuser) ActionContext.getContext().getSession()
				.get("sysuser");
		ArrayList<Linkmenu> menuList = new ArrayList<Linkmenu>();
		Set<Userrole> userroles = user.getUserroles();
		Iterator<Userrole> it = userroles.iterator();
		// 遍历角色
		while (it.hasNext()) {
			Userrole userrole = it.next();
			Role role = userrole.getRole();
			Set<Roleright> rights = role.getRolerights();
			Iterator<Roleright> rightsIt = rights.iterator();
			// 遍历功能模块
			while (rightsIt.hasNext()) {
				Roleright right = rightsIt.next();
				Functionmodule module = right.getFunctionmodule();

				QueryTerms queryTerms = new QueryTerms();

				CriterionAndOrder linkMenuCriterionAndOrder = new CriterionAndOrder();
				linkMenuCriterionAndOrder.setCriteriaName(Linkmenu.class
						.getName());

				linkMenuCriterionAndOrder.getCriterionList().add(
						Restrictions.eq("functionModuleId",
								module.getModuleId()));

				queryTerms.getCriterionAndOrderList().add(
						linkMenuCriterionAndOrder);

				result = linkMenuServiceImpl.getLinkMenuList(queryTerms);
				if (!menuList.containsAll((ArrayList<Linkmenu>) result
						.getData()))
					menuList.addAll((ArrayList<Linkmenu>) result.getData());
			}
		}
		// 对菜单进行排序
		Comparator<Linkmenu> comparator = new Comparator<Linkmenu>() {
			public int compare(Linkmenu m1, Linkmenu m2) {
				if (m1.getFunctionModuleRef().getSortField() == null) {
					return 0;
				}
				if (m2.getFunctionModuleRef().getSortField() == null) {
					return 0;
				} else {
					return m1
							.getFunctionModuleRef()
							.getSortField()
							.compareTo(m2.getFunctionModuleRef().getSortField());
				}
			}
		};
		Collections.sort(menuList, comparator);
		result.setData(menuList);
		return "json";

	}

	@SuppressWarnings("unchecked")
	public String getMenuListByRole1() {

		Systemuser user = (Systemuser) ActionContext.getContext().getSession()
				.get("sysuser");
		ArrayList<Linkmenu> menuList = new ArrayList<Linkmenu>();
		Set<Userrole> userroles = user.getUserroles();
		Iterator<Userrole> it = userroles.iterator();
		// 遍历角色
		while (it.hasNext()) {
			Userrole userrole = it.next();
			Role role = userrole.getRole();
			Set<Roleright> rights = role.getRolerights();
			Iterator<Roleright> rightsIt = rights.iterator();
			// 遍历功能模块
			while (rightsIt.hasNext()) {
				Roleright right = rightsIt.next();
				Functionmodule module = right.getFunctionmodule();

				ProjectionList pList = Projections.projectionList();

				// pList.add(Projections.property("LM.webpage").as("webpage"));
				pList.add(Projections.property("LM.menuName").as("menuName"));
				pList.add(Projections.property("FM.moduleLevel")
						.as("menuLevel"));
				pList.add(Projections.property("FM.moduleId").as(
						"functionModuleId"));
				pList.add(Projections.property("FM.parentModuleId").as(
						"parentFunctionModuleId"));
				pList.add(Projections.property("FM.sortField").as("sortField"));
				pList.add(Projections.property("WP.url").as("pageUrl"));

				QueryTerms queryTerms = new QueryTerms();

				CriterionAndOrder linkMenuCriterionAndOrder = new CriterionAndOrder();
				CriterionAndOrder functionModuleCriterionAndOrder = new CriterionAndOrder();
				CriterionAndOrder webPageCriterionAndOrder = new CriterionAndOrder();

				linkMenuCriterionAndOrder.setCriteriaName(Linkmenu.class
						.getName());
				linkMenuCriterionAndOrder.setCriteriaAsName("LM");

				linkMenuCriterionAndOrder.getCriterionList().add(
						Restrictions.eq("LM.functionModuleId",
								module.getModuleId()));

				functionModuleCriterionAndOrder
						.setCriteriaName("functionModuleRef");
				functionModuleCriterionAndOrder.setCriteriaAsName("FM");

				webPageCriterionAndOrder.setCriteriaName("webpage");
				webPageCriterionAndOrder.setCriteriaAsName("WP");

				queryTerms.getCriterionAndOrderList().add(
						linkMenuCriterionAndOrder);
				queryTerms.getCriterionAndOrderList().add(
						functionModuleCriterionAndOrder);
				queryTerms.getCriterionAndOrderList().add(
						webPageCriterionAndOrder);

				// 要查询成的属性
				queryTerms.setProjectionList(pList);
				// 要序列化成的实体类
				queryTerms.setPersistentClass(Linkmenu.class);

				result = linkMenuServiceImpl.getLinkMenuList(queryTerms);
				if (!menuList.containsAll((ArrayList<Linkmenu>) result
						.getData()))
					menuList.addAll((ArrayList<Linkmenu>) result.getData());
			}
		}
		// 对菜单进行排序
		Comparator<Linkmenu> comparator = new Comparator<Linkmenu>() {
			public int compare(Linkmenu m1, Linkmenu m2) {
				if (m1.getSortField() == null) {
					return 0;
				}
				if (m2.getSortField() == null) {
					return 0;
				} else {
					return m1.getSortField().compareTo(m2.getSortField());
				}
			}
		};
		Collections.sort(menuList, comparator);
		result.setData(menuList);
		return "json";

	}


	public String getMenuListByRole() {

		Systemuser user = (Systemuser) ActionContext.getContext().getSession()
				.get("sysuser");
		
		String sql="select L.menuName,W.url,F.moduleLevel,F.moduleId,F.parentModuleId  from linkmenu L left join webpage W on L.webPageId=W.pageId left join functionmodule F on L.functionModuleId=F.moduleId left join roleright R on F.moduleId=R.functionModuleId left join role RO on R.roleId=RO.roleId left join userrole UR on RO.roleId=UR.roleId where UR.userId='"+ user.getUserId() +"' order by F.moduleLevel,F.sortField;";

		result = linkMenuServiceImpl.getLinkMenuList(sql);

		return "json";
	}

	public LinkMenuServiceImpl getLinkMenuServiceImpl() {
		return linkMenuServiceImpl;
	}

	public void setLinkMenuServiceImpl(LinkMenuServiceImpl linkMenuServiceImpl) {
		this.linkMenuServiceImpl = linkMenuServiceImpl;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getTextMenuId() {
		return textMenuId;
	}

	public void setTextMenuId(String textMenuId) {
		this.textMenuId = textMenuId;
	}

	public String getTextWebPageId() {
		return textWebPageId;
	}

	public void setTextWebPageId(String textWebPageId) {
		this.textWebPageId = textWebPageId;
	}

	public String getTextareaDescription() {
		return textareaDescription;
	}

	public void setTextareaDescription(String textareaDescription) {
		this.textareaDescription = textareaDescription;
	}

	public String getTextMenuName() {
		return textMenuName;
	}

	public void setTextMenuName(String textMenuName) {
		this.textMenuName = textMenuName;
	}

	public String getTextFunctionModuleId() {
		return textFunctionModuleId;
	}

	public void setTextFunctionModuleId(String textFunctionModuleId) {
		this.textFunctionModuleId = textFunctionModuleId;
	}

	public String getSelectModuleLevel() {
		return selectModuleLevel;
	}

	public void setSelectModuleLevel(String selectModuleLevel) {
		this.selectModuleLevel = selectModuleLevel;
	}

	public String getSelectParentModuleId() {
		return selectParentModuleId;
	}

	public void setSelectParentModuleId(String selectParentModuleId) {
		this.selectParentModuleId = selectParentModuleId;
	}

	public ResultModel getResult() {
		return result;
	}

	public void setResult(ResultModel result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
