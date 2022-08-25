package com.marketing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketing.entities.Lead;
import com.marketing.repositories.LeadRepository;



@Service
public class LeadServiceImpl implements LeadService {
	
	@Autowired
	private LeadRepository leadrepo;

	@Override
	public void saveLead(Lead lead) {
		leadrepo.save(lead);
		
	}

	@Override
	public List<Lead> listLeads() {
		List<Lead> result = leadrepo.findAll();
		return result;
	}

	@Override
	public void deleteLeadById(long id) {
		leadrepo.deleteById(id);
	}

	@Override
	public Lead getOneLead(long id) {
		Optional<Lead> findById = leadrepo.findById(id);
		Lead lead = findById.get();
		return lead;
	}

}
