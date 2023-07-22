package com.pe.aseyco.technical.challenge.repository.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pe.aseyco.technical.challenge.common.constant.Constant;
import com.pe.aseyco.technical.challenge.common.exception.GenericException;
import com.pe.aseyco.technical.challenge.repository.ICompanyRepository;
import com.pe.aseyco.technical.challenge.repository.company.model.CompanyDocument;

@Repository
public class CompanyRepositoryImpl implements ICompanyRepository {

	private static final Logger logger = LoggerFactory.getLogger(CompanyRepositoryImpl.class);
	
	private static final String STATUS_ACTIVATE = "ACTIVO";
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
	@Override
	public Optional<CompanyDocument> findByRUC(final String ruc) throws GenericException {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("ruc").is(ruc)
					.and("status").is(STATUS_ACTIVATE));
            
			CompanyDocument companyDocument = this.mongoTemplate.findOne(query, CompanyDocument.class);
			
			return companyDocument != null ? Optional.of(companyDocument) : Optional.empty();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException(Constant.ERROR_GENERIC_RESPONSE_CODE, e.getMessage(), e.getCause());
		}
	}

	@Override
	public void save(final CompanyDocument companyDocument) throws GenericException {
		companyDocument.setRegistrationDate(LocalDateTime.now());
		companyDocument.setUpdateDate(LocalDateTime.now());
		
		try {
			this.mongoTemplate.indexOps(CompanyDocument.class)
			.ensureIndex(new Index().named("find_by_ruc").unique().on("ruc", Sort.Direction.ASC));
			this.mongoTemplate.insert(companyDocument);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException(Constant.ERROR_GENERIC_RESPONSE_CODE, e.getMessage(), e.getCause());
		}
	}

}
