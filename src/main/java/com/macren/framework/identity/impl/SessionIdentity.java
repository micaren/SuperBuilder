package com.macren.framework.identity.impl;

import java.io.Serializable;

import com.macren.framework.identity.Identity;


public class SessionIdentity implements Identity {

	private Serializable accessToken = null;

	public SessionIdentity(Serializable accessToken) {
		this.accessToken = accessToken;
	}

	public Serializable getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(Serializable accessToken) {
		this.accessToken = accessToken;
	}

}
