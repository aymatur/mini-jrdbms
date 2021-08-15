package rdnms;

import rdnms.common.CommonConstants;

public class PageId {

	long page_id;

	public PageId() {
		this.page_id = CommonConstants.INVALID_PAGE_ID;
	}

	public boolean valid() {

		if(this.page_id == CommonConstants.INVALID_PAGE_ID) return false;

		return true;
	}

}
