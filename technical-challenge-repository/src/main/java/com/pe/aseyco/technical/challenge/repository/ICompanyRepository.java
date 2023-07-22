package com.pe.aseyco.technical.challenge.repository;

import java.util.Optional;

import com.pe.aseyco.technical.challenge.common.exception.GenericException;
import com.pe.aseyco.technical.challenge.repository.company.model.CompanyDocument;

public interface ICompanyRepository {
	
    Optional<CompanyDocument> findByRUC(final String ruc) throws GenericException;
    void save(final CompanyDocument companyDocument) throws GenericException;
}
