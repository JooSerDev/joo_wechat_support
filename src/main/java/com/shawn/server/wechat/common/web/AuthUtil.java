package com.shawn.server.wechat.common.web;

import javax.servlet.http.HttpServletRequest;

import org.sword.wechat4j.oauth.OAuthException;
import org.sword.wechat4j.oauth.OAuthManager;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenResponse;
import org.sword.wechat4j.oauth.protocol.get_userinfo.GetUserinfoRequest;
import org.sword.wechat4j.oauth.protocol.get_userinfo.GetUserinfoResponse;

/**
 * 微信网页接口<br>
 * 用户认证工具类
 * 
 * @author Shawnpoon
 *
 */
public class AuthUtil {

	private static final String CODE = "code";
	private static final String STATE = "state";

	/**
	 * 获得授权回调中的code和state参数,{ code, state }
	 * 
	 * @param request
	 * @return
	 */
	public static String[] getCodeAndState(HttpServletRequest request) {
		String code = request.getParameter(CODE);
		String state = request.getParameter(STATE);
		return new String[] { code, state };
	}

	/**
	 * 通过snsapi_base（静默授权）发起网页授权时，获得用户openid
	 * 
	 * @param code
	 * @return
	 * @throws OAuthException
	 */
	public static String getOpenidByCodeInSnsbase(String code) throws OAuthException {
		GetAccessTokenRequest accessTokenRequest = new GetAccessTokenRequest(code);
		GetAccessTokenResponse accessTokenResponse = OAuthManager.getAccessToken(accessTokenRequest);
		String openid = accessTokenResponse.getOpenid();
		return openid;
	}

	/**
	 * 通过snsapi_userinfo发起网页授权时，获得用户基本信息
	 * 
	 * @param code
	 * @return
	 * @throws OAuthException
	 */
	public static GetUserinfoResponse getUserinfoByCodeInSnsinfo(String code) throws OAuthException {
		GetAccessTokenRequest accessTokenRequest = new GetAccessTokenRequest(code);
		GetAccessTokenResponse accessTokenResponse = OAuthManager.getAccessToken(accessTokenRequest);
		GetUserinfoRequest getUserinfoRequest = new GetUserinfoRequest(accessTokenResponse.getAccess_token(),
				accessTokenResponse.getOpenid());
		GetUserinfoResponse getUserinfoResponse = OAuthManager.getUserinfo(getUserinfoRequest);
		return getUserinfoResponse;
	}

}
