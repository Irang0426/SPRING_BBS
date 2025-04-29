package com.bbs.demo.domain;

public class Admin {
	private String url;
	private String sortby;
	private int page;
	private int limit;
	private String orderby;
	private int offset = page*limit;
	private int totalPageCount;
	
	public String getUrl() {
		return url;
	}
	public Admin setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getSortby() {
		return sortby;
	}
	public Admin setSortby(String sortby) {
		this.sortby = sortby;
		return this;
	}
	public int getPage() {
		return page;
	}
	public Admin setPage(int page) {
		this.page = page;
		return this;
	}
	public int getLimit() {
		return limit;
	}
	public Admin setLimit(int limit) {
		this.limit = limit;
		return this;
	}
	public String getOrderby() {
		return orderby;
	}
	public Admin setOrderby(String orderby) {
		this.orderby = orderby;
		return this;
	}
	public Admin setOffset() {
		this.offset = page*limit;
		return this;
	}
	public int getOffset() {
		return offset;
	}
	public Admin setTotalPageCount(int totalCount) {
		if(totalCount % this.limit != 0) {
			this.totalPageCount = (int)(totalCount / this.limit) + 1;
		}
		else {
			this.totalPageCount = (int)(totalCount / this.limit);
		}
		
		return this;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
}
