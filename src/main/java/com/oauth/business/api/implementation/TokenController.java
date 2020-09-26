package com.oauth.business.api.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.oauth.business.service.UserService;
import com.oauth.entity.User;


@RestController
public class TokenController {
	@Autowired
	private DefaultTokenServices defaultTokenServices;
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	ClientRegistrationService clientRegistrationService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailsService userDetailsService;


	@RequestMapping(value = "/oauth/revoke/token", method = RequestMethod.POST)
	public boolean invalidate(@RequestParam("token") String value) {
		return defaultTokenServices.revokeToken(value);
	}

	@RequestMapping(value = "/oauth/logout", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('role_user')")
	public boolean logOut() {
		User user = ((User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal());
		try {
			for (ClientDetails client : clientRegistrationService.listClientDetails()) {
				for (final OAuth2AccessToken token : tokenStore.findTokensByClientIdAndUserName(client.getClientId(),
						user.getUsername())) {
					tokenStore.removeRefreshToken(token.getRefreshToken());
					tokenStore.removeAccessToken(token);
				}

			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}