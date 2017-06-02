package com.paypal.core.object;

import com.google.gson.annotations.SerializedName;
import com.paypal.core.authorization.Authorization;

import java.util.Date;

public class AccessToken implements Authorization {

	private final transient Date createDate = new Date();

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("token_type")
	private String tokenType = "Bearer";

	@SerializedName("expires_in")
    private int expiresIn;

	public boolean isExpired() {
    	Date expireDate = new Date(createDate.getTime() + (expiresIn * 1000));
    	return new Date().after(expireDate);
    }

	public String accessToken() {
		return accessToken;
	}

	public String tokenType() {
		return tokenType;
	}

	public int expiresIn() {
		return expiresIn;
	}

	public Date createDate() {
		return createDate;
	}

	@Override
	public String authorizationString() {
		return String.format("Bearer %s", accessToken);
	}
}
