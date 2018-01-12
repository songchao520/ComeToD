package com.changuang.domain.huanxin.api.impl;

import com.changuang.domain.huanxin.api.AuthTokenAPI;
import com.changuang.domain.huanxin.comm.TokenUtil;

public class EasemobAuthToken implements AuthTokenAPI{

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
