package com.bbs.demo.domain;

public class Page {
	private int page;		// 현재 page 위치
	private int pageSize;	// 한 page 내용의 개수
	private int pageLen; 	// page 바로가기 개수
	private int boardPos;	// 현재 게시판 위치
	
	
	public int getBoardPos() {
		return boardPos;
	}

	public void setBoardPos(int boardPos) {
		this.boardPos = boardPos;
	}

	public Page() {
		this.page = 1;
		this.pageSize = 10;
		this.pageLen = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageLen() {
		return pageLen;
	}

	public void setPageLen(int pageLen) {
		this.pageLen = pageLen;
	}
	
	public int getOffset() {
		return (this.page - 1) * this.pageSize;
	}
}
