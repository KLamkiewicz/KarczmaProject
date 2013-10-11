package pl.karczma.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(filterName = "backFilter", urlPatterns = "*.hmtl")
@SuppressWarnings("unused")
public class BackFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//Wylacz cachowanie - przy uzyciu back buttonu strona musi zostac ponownie pobrana z serwera
		httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		httpServletResponse.setDateHeader("Expires", 0);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}