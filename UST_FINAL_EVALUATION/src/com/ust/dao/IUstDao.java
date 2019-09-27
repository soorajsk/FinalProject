package com.ust.dao;

import java.util.List;

import com.ust.beans.User;
import com.ust.beans.VendorContact;

public interface IUstDao {

	public abstract User selectRole(String username, String password);

	public abstract List<VendorContact> getVendor();

	public abstract List<VendorContact> getVendorByName(String vendor_name);

	//------------get vendor details by id
	public abstract VendorContact getVendorById(int vendor_id);

	public abstract int saveVendor(VendorContact vc);

	//-------------- update vendor
	public abstract int updateVendor(int vendor_id, VendorContact vc);

	public abstract List<VendorContact> getContactDetailsByVId(int vid);

	//--------------- to disable a vendor
	public abstract int disableVendor(int vId);

}