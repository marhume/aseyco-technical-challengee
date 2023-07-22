package com.pe.aseyco.technical.challenge.core.service;

import com.pe.aseyco.technical.challenge.common.exception.GenericException;
import com.pe.aseyco.technical.challenge.core.dto.CompanyDto;
import com.pe.aseyco.technical.challenge.core.dto.CompanyRegisterRqDto;

public interface ICompanyDBService {

	CompanyDto getCompanyByRUC(final String ruc) throws GenericException;
    void save(final CompanyRegisterRqDto request) throws GenericException;
}
