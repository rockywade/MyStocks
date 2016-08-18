package com.cxstock.action.classes;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.classes.ClassesBiz;

@SuppressWarnings("serial")
public class ClassesAction extends BaseAction  {
	
	private ClassesBiz classesBiz;
	
	private String ssyq;

	public ClassesBiz getClassesBiz() {
		return classesBiz;
	}


	public void setClassesBiz(ClassesBiz classesBiz) {
		this.classesBiz = classesBiz;
	}




	public String getSsyq() {
		return ssyq;
	}


	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}


	/**
	 * 下拉数据
	 */
	public String findClasses() {
		try {
			this.outListString(classesBiz.findClasses());
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	/**
	 * 下拉数据
	 */
	public String findClassesBySsyq() {
		try {
			this.outListString(classesBiz.findClassesBySsyq(this.ssyq));
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
}
