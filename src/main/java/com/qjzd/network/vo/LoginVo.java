package com.qjzd.network.vo;

import javax.validation.constraints.NotNull;

import com.qjzd.network.validator.IsMobile;
import org.hibernate.validator.constraints.Length;


public class LoginVo {
	
	@NotNull
	@Length(min=5)
	private String username;
	
	@NotNull
	@Length(min=32)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginVo{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
