package rdnms;

import java.util.List;

public class Buffer {

	long page_id;
	List<Integer> page;
	boolean is_dirty;

	public long getPage_id() {
		return page_id;
	}
	public void setPage_id(long page_id) {
		this.page_id = page_id;
	}
	public List<Integer> getPage() {
		return page;
	}
	public void setPage(List<Integer> page) {
		this.page = page;
	}
	public boolean isIs_dirty() {
		return is_dirty;
	}
	public void setIs_dirty(boolean is_dirty) {
		this.is_dirty = is_dirty;
	}

}
