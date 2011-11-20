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

import java.util.Iterator;

import org.ws13.vaadin.osgi.dm.services.Module;
import org.ws13.vaadin.osgi.dm.services.ModuleService;
import org.ws13.vaadin.osgi.dm.services.ModuleServiceListener;

import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;

public class ModuleDemoApp extends Application implements ModuleServiceListener {

	private static final long serialVersionUID = -3269407406426655882L;

	private ModuleService moduleService;

	public ModuleDemoApp() {
	}

	private TabSheet tabs;

	@Override
	public void init() {
		tabs = new TabSheet();
		tabs.setSizeFull();

		for (Module module : moduleService.getModules()) {
			tabs.addTab(module.createComponent(), module.getName(), null);
		}

		setMainWindow(new Window("Module Demo Application", tabs));

		System.out.println("ModuleDemoApp: Application initializing, adding module service listener");
		moduleService.addListener(this);
	}

	@Override
	public void close() {
		System.out.println("ModuleDemoApp: Application closing, removing module service listener");
		moduleService.removeListener(this);
		super.close();
	}

	@Override
	public void moduleRegistered(ModuleService source, Module module) {
		System.out.println("ModuleDemoApp: Module registered, adding tab");
		tabs.addTab(module.createComponent(), module.getName(), null);
	}

	@Override
	public void moduleUnregistered(ModuleService source, Module module) {
		System.out.println("ModuleDemoApp: Module unregistered, removing tab");
		Iterator<Component> it = tabs.getComponentIterator();
		while (it.hasNext()) {
			Component c = it.next();
			if (tabs.getTab(c).getCaption().equals(module.getName())) {
				tabs.removeComponent(c);
				return;
			}
		}
	}

	/**
	 * @return the moduleService
	 */
	public ModuleService getModuleService() {
		return moduleService;
	}

	/**
	 * @param moduleService the moduleService to set
	 */
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

}
