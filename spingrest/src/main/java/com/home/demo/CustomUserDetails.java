package com.home.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.home.demo.entity.Role;
import com.home.demo.entity.User;

public class CustomUserDetails implements UserDetails {
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> auths;

	public CustomUserDetails() {
	}

	public CustomUserDetails(User user) {
		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
		final List<GrantedAuthority> authorities = new ArrayList<>();
		for (final Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
		}
		this.auths = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.auths;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
