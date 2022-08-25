package com.marketing.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.dto.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;

@Controller
public class LeadController {

	@Autowired
	private LeadService leadServ;
	
	
	@RequestMapping("/createLead")
	public String viewCreateLead()
	{
		return  "create_lead";
	}
	
	
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute("lead") Lead lead , ModelMap model)
	{
		leadServ.saveLead(lead);
		model.addAttribute("msg" , "Lead is Saved !!!");
		return "create_lead" ;
	}
	
	
	
	@RequestMapping("/listall")
	public String listLeads( ModelMap model)
	{
		List<Lead> results = leadServ.listLeads();
		model.addAttribute("Results", results);
		return "Leads_Search_Results";
	}

	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") long id , ModelMap model)
	{
		leadServ.deleteLeadById(id);
		
		List<Lead> results = leadServ.listLeads();
		model.addAttribute("Results", results);
		return "Leads_Search_Results";
	}
	
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id") long id  , ModelMap model)
	{
		Lead lead = leadServ.getOneLead(id);
		model.addAttribute("lead", lead);
		return "update_lead" ;
	}
	
	@RequestMapping("/updateLead")
	public String updateOneLeadData(LeadData data , ModelMap model )
	{
		Lead lead = new Lead();
		lead.setId(data.getId());
		lead.setFirstName(data.getFirstName());
		lead.setLastName(data.getLastName());
		lead.setEmail(data.getEmail());
		lead.setMobile(data.getMobile());
		leadServ.saveLead(lead);
		List<Lead> results = leadServ.listLeads();
		model.addAttribute("Results", results);
		return "Leads_Search_Results";
	}
	
}
