package com.pe.aseyco.technical.challenge.core.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.aseyco.technical.challenge.common.exception.GenericException;
import com.pe.aseyco.technical.challenge.core.dto.CompanyDto;
import com.pe.aseyco.technical.challenge.core.dto.CompanyRegisterRqDto;
import com.pe.aseyco.technical.challenge.core.service.ICompanyDBService;
import com.pe.aseyco.technical.challenge.repository.ICompanyRepository;
import com.pe.aseyco.technical.challenge.repository.company.model.CompanyDocument;

@Service
public class CompanyDBServiceImpl implements ICompanyDBService {

    @Autowired
    private ICompanyRepository companyRepository;

	@Override
	public CompanyDto getCompanyByRUC(final String ruc) throws GenericException {
		Optional<CompanyDocument> optional = this.companyRepository.findByRUC(ruc);
		CompanyDto companyDto = new CompanyDto();
		BeanUtils.copyProperties(optional, companyDto);
		return companyDto;
	}

	@Override
	public void save(CompanyRegisterRqDto request) throws GenericException {
		CompanyDocument companyDocument = new CompanyDocument();
		BeanUtils.copyProperties(request, companyDocument);
		this.companyRepository.save(companyDocument);
	}

}
