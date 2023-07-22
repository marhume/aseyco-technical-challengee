package com.pe.aseyco.technical.challenge.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyGeneric implements Serializable {

    private static final long serialVersionUID = -1L;

	private String ruc;
	private String businessName;
	private String status;
	private String address;
	private String ubigeo;
	private String department;
	private String province;
	private String district;
}
