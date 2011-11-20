/*******************************************************************************
 * Copyright 2011 ctranxuan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.ws13.vaadin.osgi.dm.app;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;


@SuppressWarnings("serial")
public class SpringApplicationServlet extends AbstractApplicationServlet {

	/** Default application bean name in Spring application context. */
	private static final String DEFAULT_APP_BEAN_NAME = "application";

	/** Application bean name in Spring application context. */
	private String name;

	/**
	 * Get and stores in the servlet the application bean's name in the Spring's
	 * context. It's expected to be configured as a the servlet
	 * &lt;code&gt;init-param&lt;/code&gt; named applicationBeanName. If no
	 * param is found, the default is "application".
	 * 
	 * @see AbstractApplicationServlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		String name = config.getInitParameter("applicationBeanName");

		this.name = name == null ? DEFAULT_APP_BEAN_NAME : name;
	}

	/**
	 * Get the application bean in Spring's context.
	 * 
	 * @see AbstractApplicationServlet#getNewApplication(HttpServletRequest)
	 */
	@Override
	protected Application getNewApplication(HttpServletRequest request) throws ServletException {

		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		if (wac == null) {
			throw new ServletException("Cannot get an handle on Spring's context. Is Spring running?"
					+ "Check there's an org.springframework.web.context.ContextLoaderListener configured.");
		}

		String[] beanDefinitionNames = wac.getBeanDefinitionNames();
		for (String string : beanDefinitionNames) {
			System.out.println("SpringApplicationServlet.getNewApplication() ->"  + string);
		}

		Application bean = wac.getBean(name, Application.class);

		if (!(bean instanceof Application)) {

			throw new ServletException("Bean " + name + " is not of expected class Application");
		}

		return bean;
	}

	/**
	 * Get the application class from the bean configured in Spring's context.
	 * 
	 * @see AbstractApplicationServlet#getApplicationClass()
	 */
	@Override
	protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {

		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		if (wac == null) {
			throw new ClassNotFoundException("Cannot get an handle on Spring's context. Is Spring running? "
					+ "Check there's an org.springframework.web.context.ContextLoaderListener configured.");
		}
		String[] beanDefinitionNames = wac.getBeanDefinitionNames();
		for (String string : beanDefinitionNames) {
			System.out
			.println("SpringApplicationServlet.getApplicationClass() " + string);
		}

		Application bean = wac.getBean(name, Application.class);

		if (bean == null) {

			throw new ClassNotFoundException("No application bean found under name " + name);
		}

		return bean.getClass();
	}
}
