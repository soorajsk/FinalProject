package com.ust.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.beans.User;
import com.ust.beans.VendorContact;
import com.ust.dao.IUstDao;

@RestController
public class UstController {

	@Autowired	
IUstDao udao;


//verify login
	@RequestMapping(value = "/api/user/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public User selectUser(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		return udao.selectRole(username,password);
	}
	
	//view vendors
	
		@RequestMapping(value = "/api/vendor/{vendor_name}", method = RequestMethod.GET)
		@ResponseBody
		public List getVendor(Model m, @PathVariable("vendor_name") String vendor_name) {
			List list;
			if (vendor_name.equals("null")) {
				list = udao.getVendor();
			} else {
				list = udao.getVendorByName(vendor_name);
			}

			return list;
		}
		
		// view vendor list by id
		@RequestMapping(value = "/api/vendors/{vendor_id}", method = RequestMethod.GET)
		@ResponseBody
		public VendorContact getvendors(Model m, @PathVariable("vendor_id") int vendor_id) {
			
			return  udao.getVendorById(vendor_id);
			
		}

		// Add Vendor
		@ResponseBody
		@RequestMapping(value = "/api/insertvendor", method = RequestMethod.POST)
		public void insertVendor(@RequestBody VendorContact vc) throws ParseException {
			udao.saveVendor(vc);
		}
		
		// update Vendor
		@ResponseBody
		@RequestMapping(value = "/api/updatevendor", method = RequestMethod.PUT)
		public void updateDoctor(@RequestBody VendorContact vc) throws ParseException {
			int vendor_id = vc.getVendor_id();
			int cont_id = vc.getCont_id();
			udao.updateVendor(vendor_id, vc);
		}
		
		// to disable a vendor

		@RequestMapping(value = "/api/disablevendor/{vId}", method = RequestMethod.PUT)
		@ResponseBody
		public void disableStaff(@PathVariable("vId") int vId) {
		udao.disableVendor(vId);
		}
		
		// view contactdetails by vendor id

		@RequestMapping(value = "/api/contactDetails/{vId}", method = RequestMethod.GET)
		@ResponseBody
		public List getContactDetails(Model m,@PathVariable("vId") int vId) {
			List list;
		list=udao.getContactDetailsByVId(vId);
		return list;

		}
}
