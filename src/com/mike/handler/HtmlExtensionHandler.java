package com.mike.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class HtmlExtensionHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		if (target.endsWith(".html")) {
			target = target.substring(0, target.length() - 5);
		}
		nextHandler.handle(target, request, response, isHandled);
	}

}
