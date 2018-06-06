package com.bdqn.syht.common;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.bdqn.syht.pojo.delivery.Promotion;


// 自定义 分页数据封装对象 
@XmlRootElement(name = "pageBean")//WebService传输格式	传pageBean
@XmlSeeAlso({ Promotion.class })//指定远程通信的泛型
public class PageBean<T> {

	private long totalCount; // 总记录数
	private List<T> pageData; // 当前页数据集合

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

}
