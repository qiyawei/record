package com.kaishengit.entity;

import java.util.List;

public class Pager<T> {
	private Long pageNo;
	private Long start;
	private Long pageSize;
	private Long totalSize;
	private Long totalPages;
	private List<T> items;
	

	
	
	
	public Pager(){};
	public Pager(Long pageSize,Long totalSize){
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		Long temp = totalSize / pageSize;
		if(totalSize % pageSize != 0){
			temp++;
		}
		this.totalPages = temp;
	}	
	public Pager(Long pageNo,Long pageSize,Long totalSize){
		this(pageSize, totalSize);
		if(pageNo < 1){
			pageNo = 1l;
		}
		
		if(pageNo > totalPages) {
			pageNo = totalPages;
		}
		Long start = (pageNo-1)*pageSize;
		//System.out.println(start);
		if(pageNo == 0){
			this.start = 0L;
		}else{
			this.start = start;
		}
		
		this.pageNo = pageNo;
	}
	
	
	
	public Long getPageNo() {
		return pageNo;
	}
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	
}
