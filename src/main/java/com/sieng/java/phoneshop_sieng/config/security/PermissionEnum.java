package com.sieng.java.phoneshop_sieng.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PermissionEnum {
	BRAND_READ("brand:read"),
	BRAND_WRITE("brand:write"),
	MODEL_READ("model:read"),
	MODEL_WRITE("model:write");
	private String description;

}
