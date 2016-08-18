package com.cxstock.biz.leaveinfo.dto;


/**
 * 学生信息接收数据实体类
 * @author wzx
 *
 * 2016-5-26
 */
public class studentDTO {
	
	private String studentnum;
	private String studentname;
	private String majorname;
	private String classcode;
	private String studentdorm;
	private String studentphonenum;
	
	public studentDTO( String studentnum,String studentname,String majorname,
			String classcode, String studentdorm,String studentphonenum
			) {
		super();
		this.studentname = studentname;
		this.classcode = classcode;
		this.majorname = majorname;
		this.classcode = classcode;
		this.studentdorm = studentdorm;
		this.studentphonenum = studentphonenum;
	
	}

}
