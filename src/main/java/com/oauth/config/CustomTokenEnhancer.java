package com.oauth.config;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.oauth.entity.User;


public class CustomTokenEnhancer extends JwtAccessTokenConverter {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		OAuth2RefreshToken oAuth2RefreshToken = accessToken.getRefreshToken();
		String refreshToken = "";
		User user = (User) authentication.getPrincipal();
		JsonParser objectMapper = JsonParserFactory.create();
		Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

		if (info.containsKey(TOKEN_ID)) {
			refreshToken = info.get(TOKEN_ID).toString();
		}
		info.put("email", user.getEmail());
		info.put("id", user.getId());
		DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
		defaultOAuth2AccessToken.setAdditionalInformation(info);
		return super.enhance(defaultOAuth2AccessToken, authentication);
	}
}