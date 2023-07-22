package com.pe.aseyco.technical.challenge.common.util;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pe.aseyco.technical.challenge.common.constant.Constant;
import com.pe.aseyco.technical.challenge.common.exception.GenericException;

@Component
public class JwtAuth {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuth.class);
	private final static String ISSUER = "Aseyco";
	private final static String SECRET = "aseyco-technical";
	private Algorithm algorithm = Algorithm.HMAC256("aseyco-technical");

	@PostConstruct
	public void init() {
		this.algorithm = Algorithm.HMAC256(SECRET);
	}

	public String jwtTokenGenerate() {
		String jwtToken = JWT.create().withIssuer(ISSUER).withSubject("technical-challenge").withClaim("claims", "Test")
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
				.withJWTId(UUID.randomUUID().toString()).withNotBefore(new Date(System.currentTimeMillis() + 1000L))
				.sign(algorithm);

		return jwtToken;
	}

	public void verifier(final String jwtToken) throws GenericException {
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
		try {
			verifier.verify(jwtToken);
		} catch (JWTVerificationException e) {
			logger.error(e.getMessage());
			throw new GenericException(Constant.ERROR_INVALID_TOKEN_CODE, "Error, el JWT es inválido");
		}
	}

	public String getJwtFromAuth(String authorization) throws GenericException {
		String[] authorizationArray = authorization.split(" ");

		if (authorizationArray.length < 2) {
			throw new GenericException(Constant.ERROR_GENERIC_RESPONSE_CODE, "Error, el token de de autorización es inválido");
		}
		return authorizationArray[1];
	}

}
