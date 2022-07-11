package com.savvycom.auth_service.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;

	/**
	 * Mặc định, Spring sẽ tìm kiếm các phụ thuộc được chú thích bởi @Autowired theo kiểu dữ liệu.
	 * Nếu có nhiều hơn một bean có cùng một kiểu dữ liệu được tiêm trong spring container,
	 * Spring sẽ ném ra một NoUniqueBeanDefinitionException.
	 * Bằng cách sử dụng @Qualifier annotation,
	 * chúng ta có thể chỉ định rõ bean nào được sử dụng khi có nhiều bean có cùng kiểu dữ liệu.
	 */
	@Qualifier("UserForAuthorization")
	private final UserDetailsService userDetailsService;

	@Value("${security.oauth2.client.client-id:clientId}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret:secret}")
	private String clientSecret;

	@Value("${security.oauth2.client.access-token-validity-seconds:21600}") // 6 hours
	private int accessTokenValiditySeconds;

	@Value("${security.oauth2.client.refresh-token-validity-seconds:43200}") // 12 hours
	private int refreshTokenValiditySeconds;

	@Value("${security.oauth2.client.authorized-grant-types:authorization_code}")
	private String[] authorizedGrantTypes;

	@Value("${security.oauth2.client.scope:read}")
	private String[] scope;

	@Value("${security.oauth2.client.resource-id:nx-api}")
	private String resourceId;

	@Value("${security.oauth2.client.registered-redirect-uri:/callback}")
	private String redirectUri;

	/**
	 * Config parameter for Keystore File
	 */
	private static final String KEY_STORE_FILE = "nx-jwt.jks";
	private static final String KEY_STORE_PASSWORD = "nx-pass";
	private static final String KEY_ALIAS = "nx-oauth-jwt";
	private static final String JWK_KID = "nx-key-id";

	/**
	 * 	Nơi lưu trữ token
	 */
	@Bean
	public TokenStore tokenStore(@Qualifier("customizeJwtAccessTokenConverter") JwtAccessTokenConverter jwtAccessTokenConverter) {
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	/**
	 * Convert sang JWT (mã hóa dưới dạng JWS) với kiểu customHeader để thêm kid param vào header.
	 *  Tham số tiêu đề con (key ID) là một gợi ý cho biết khóa nào đã được sử dụng để bảo mật JWS.(Nếu có nhiều bộ khóa đc sd)
	 *
	 * @return new JwtCustomHeadersAccessTokenConverter(customHeaders, keyPair());
	 */
	@Bean("customizeJwtAccessTokenConverter")
	public JwtAccessTokenConverter accessTokenConverter() {
		Map<String, String> customHeaders = Collections.singletonMap("kid", JWK_KID);
		return new JwtCustomHeadersAccessTokenConverter(customHeaders, keyPair());
	}

	/**
	 * Tạo KeyPair để phục vụ cho TokenStore với cặp khóa KeyPair:
	 * private key để kí token
	 * public key để xác thực token
	 * @return KeyPair
	 */
	@Bean
	public KeyPair keyPair() {
		ClassPathResource ksFile = new ClassPathResource(KEY_STORE_FILE);
		KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray());
		return ksFactory.getKeyPair(KEY_ALIAS);
	}

	/**
	 * Tạo JWKSet
	 * @return new JWKSet(builder.build());
	 */
	@Bean
	public JWKSet jwkSet() {
		RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic()).keyUse(KeyUse.SIGNATURE)
				.algorithm(JWSAlgorithm.RS256)
				.keyID(JWK_KID);
		return new JWKSet(builder.build());
	}


	/**
	 * Triển khai client detail của OAuth2 trong bộ nhớ
	 * @param clients the client details configurer
	 * @throws Exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient(clientId)
				.secret(clientSecret)
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
				.refreshTokenValiditySeconds(refreshTokenValiditySeconds)
				.authorizedGrantTypes(authorizedGrantTypes)
				.scopes(scope)
				.redirectUris(redirectUri)
				.resourceIds(resourceId);
	}

	/**
	 * filter chain
	 * @param security a fluent configurer for security features
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("permitAll()")
				.allowFormAuthenticationForClients();
	}

	/**
	 * Cấu hình các thuộc tính và chức năng nâng cao của các điểm cuối Máy chủ Ủy quyền.
	 * @param endpoints the endpoints configurer
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.userDetailsService(userDetailsService)
				.accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager)
				.tokenStore(tokenStore(accessTokenConverter()));
	}	
}