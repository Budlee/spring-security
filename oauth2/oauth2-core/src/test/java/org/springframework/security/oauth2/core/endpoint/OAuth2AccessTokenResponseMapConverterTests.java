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

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.security.oauth2.core.OAuth2AccessToken;

/**
 * Tests for {@link OAuth2AccessTokenResponseMapConverter}.
 *
 * @author Nikita Konev
 */
@Deprecated
public class OAuth2AccessTokenResponseMapConverterTests {

	private OAuth2AccessTokenResponseMapConverter messageConverter;

	@Before
	public void setup() {
		this.messageConverter = new OAuth2AccessTokenResponseMapConverter();
	}

	// gh-9685
	@Test
	public void shouldConvert() {
		// @formatter:off
		OAuth2AccessTokenResponse build = OAuth2AccessTokenResponse.withToken("access-token-value-1234")
				.tokenType(OAuth2AccessToken.TokenType.BEARER)
				.build();
		// @formatter:on
		Map<String, String> result = this.messageConverter.convert(build);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals("access-token-value-1234", result.get("access_token"));
		Assert.assertEquals("Bearer", result.get("token_type"));
		Assert.assertNotNull(result.get("expires_in"));
	}

}
