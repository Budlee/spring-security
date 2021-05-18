/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.oauth2.core.endpoint;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.security.oauth2.core.OAuth2AccessToken;

/**
 * Tests for {@link MapOAuth2AccessTokenResponseConverter}.
 *
 * @author Nikita Konev
 */
@Deprecated
public class MapOAuth2AccessTokenResponseConverterTests {

	private MapOAuth2AccessTokenResponseConverter messageConverter;

	@Before
	public void setup() {
		this.messageConverter = new MapOAuth2AccessTokenResponseConverter();
	}

	// gh-9685
	@Test
	public void shouldConvert() {
		Map<String, String> map = new HashMap<>();
		map.put("access_token", "access-token-1234");
		map.put("token_type", "bearer");
		map.put("expires_in", "3600");
		OAuth2AccessTokenResponse converted = this.messageConverter.convert(map);
		OAuth2AccessToken accessToken = converted.getAccessToken();
		Assert.assertNotNull(accessToken);
		Assert.assertEquals("access-token-1234", accessToken.getTokenValue());
		Assert.assertEquals(OAuth2AccessToken.TokenType.BEARER, accessToken.getTokenType());
		Assert.assertEquals(3600, Duration.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()).getSeconds());
	}

}
