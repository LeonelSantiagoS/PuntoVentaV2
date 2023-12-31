package com.mx.proyecto.Config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		if (authentication != null && authentication.getDetails() != null) {
			  request.getSession().invalidate();
			  response.setStatus(response.SC_OK); // respuesta 202
		      response.sendRedirect("PuntoVentaV2/Login");
		  }
	}
}