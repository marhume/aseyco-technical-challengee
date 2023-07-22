package com.pe.aseyco.technical.challenge.core.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pe.aseyco.technical.challenge.common.constant.Constant;
import com.pe.aseyco.technical.challenge.common.exception.GenericException;
import com.pe.aseyco.technical.challenge.common.util.JwtAuth;
import com.pe.aseyco.technical.challenge.core.dto.CompanyDto;
import com.pe.aseyco.technical.challenge.core.dto.CompanyRegisterRqDto;
import com.pe.aseyco.technical.challenge.core.dto.ResponseDto;
import com.pe.aseyco.technical.challenge.core.dto.Status;
import com.pe.aseyco.technical.challenge.core.service.ICompanyDBService;

@Validated
@RestController
@RequestMapping(Constant.PATH_BASE)
public class CompanyController {

	@Autowired
	private ICompanyDBService companyDBService;

	@Autowired
	private JwtAuth jwtAuth;
	
	@SuppressWarnings("rawtypes")
	@Validated
	@PostMapping(value = Constant.V1 + Constant.URL_OPERATION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> postInsuranceAffiliation(
			// Headers ->
			@RequestHeader(value = "Authorization") @Valid String authorization,
			// Body ->
			@RequestBody CompanyRegisterRqDto request) throws GenericException {

		try {
			this.jwtAuth.verifier(this.jwtAuth.getJwtFromAuth(authorization));
			
			this.companyDBService.save(request);
			return new ResponseEntity<>(getResponse(), HttpStatus.NO_CONTENT);
		} catch (GenericException e) {
			if (Constant.ERROR_INVALID_TOKEN_CODE.equals(e.getErrorCode())) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Validated
	@GetMapping(value = Constant.V1 + Constant.URL_OPERATION, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> getListInsurancePlan(
			// Headers ->
			@RequestHeader(value = "Authorization") @Valid String authorization,
            // Parameters ->
            @RequestParam(value = "ruc", required = false) @Valid @NotBlank(message = "Error, parámetro query inválido") String ruc) throws GenericException {

		try {
			this.jwtAuth.verifier(this.jwtAuth.getJwtFromAuth(authorization));
			CompanyDto companyDto = this.companyDBService.getCompanyByRUC(ruc);
			
			ResponseDto response = getResponse();
			response.setData(companyDto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (GenericException e) {
			if (Constant.ERROR_INVALID_TOKEN_CODE.equals(e.getErrorCode())) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private ResponseDto getResponse() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(new Status());
		Status status = responseDto.getStatus();
		status.setCode(Constant.SUCCESS_CODE);
		status.setCode(Constant.SUCCESS_MESSAGE);
		return responseDto;
	}

}
