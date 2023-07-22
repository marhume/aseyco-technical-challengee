package com.pe.aseyco.technical.challenge.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.aseyco.technical.challenge.common.constant.Constant;
import com.pe.aseyco.technical.challenge.common.exception.GenericException;
import com.pe.aseyco.technical.challenge.common.util.JwtAuth;
import com.pe.aseyco.technical.challenge.core.dto.CompanyRegisterRqDto;
import com.pe.aseyco.technical.challenge.core.dto.JWTRsDto;
import com.pe.aseyco.technical.challenge.core.dto.ResponseDto;
import com.pe.aseyco.technical.challenge.core.dto.Status;

@Validated
@RestController
@RequestMapping(Constant.PATH_BASE)
public class OauthTokenController {

	@Autowired
	private JwtAuth jwtAuth;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Validated
	@PostMapping(value = Constant.V1 + Constant.URL_OAUTH_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> postInsuranceAffiliation(
			// Body ->
			@RequestBody CompanyRegisterRqDto request) throws GenericException {

		ResponseDto response = getResponse();
		JWTRsDto jwtRsDto = new JWTRsDto();
		jwtRsDto.setToken(this.jwtAuth.jwtTokenGenerate());
		response.setData(jwtRsDto);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	private ResponseDto getResponse() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(new Status());
		Status status = responseDto.getStatus();
		status.setCode("0000");
		status.setCode("Success");
		return responseDto;
	}

}
