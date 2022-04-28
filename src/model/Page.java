package model;

public class Page {
	private int pageSize = 3;    // 每页显示多少条 5
	private int totalSize;  //数据库总共多少条 18
	private int totalPage;// 总页数     4    18%5 18/5+1  15%5   15/5
	private int currentPage;// 当前页    第2页
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalSize) {
		if(totalSize%pageSize == 0) {
			this.totalPage = totalSize/pageSize;
		}else{
			this.totalPage = totalSize/pageSize + 1 ;
		}
		
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
