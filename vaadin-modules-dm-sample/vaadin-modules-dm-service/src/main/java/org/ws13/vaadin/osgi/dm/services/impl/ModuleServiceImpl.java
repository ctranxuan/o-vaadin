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
package org.ws13.vaadin.osgi.dm.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.log.LogService;
import org.ws13.vaadin.osgi.dm.services.Module;
import org.ws13.vaadin.osgi.dm.services.ModuleService;
import org.ws13.vaadin.osgi.dm.services.ModuleServiceListener;

public class ModuleServiceImpl implements ModuleService {

	private LogService logService;
	private final ArrayList<Module> modules = new ArrayList<Module>();

	private final ArrayList<ModuleServiceListener> listeners = new ArrayList<ModuleServiceListener>();

	@Override
	@SuppressWarnings("unchecked")
	public synchronized void registerModule(Module module, Map<?,?> properties) {
		System.out.println("ModuleServiceImpl: Registering module " + module);
		logService.log(LogService.LOG_INFO, "ModuleServiceImpl: Registering module " + module);

		modules.add(module);
		for (ModuleServiceListener listener : (ArrayList<ModuleServiceListener>) listeners.clone()) {
			listener.moduleRegistered(this, module);
		}
	}

	@Override
	public void registerModule(Module module)
	{
		registerModule(module, Collections.emptyMap());
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized void unregisterModule(Module module, Map<?,?> properties) {
		System.out.println("ModuleServiceImpl: Unregistering module " + module);
		logService.log(LogService.LOG_INFO, "ModuleServiceImpl: Unregistering module " + module);
		modules.remove(module);
		for (ModuleServiceListener listener : (ArrayList<ModuleServiceListener>) listeners.clone()) {
			listener.moduleUnregistered(this, module);
		}
	}

	@Override
	public void unregisterModule(Module module)
	{
		unregisterModule(module, Collections.emptyMap());
	}

	@Override
	public List<Module> getModules() {
		return Collections.unmodifiableList(modules);
	}

	@Override
	public synchronized void addListener(ModuleServiceListener listener) {
		System.out.println("ModuleServiceImpl: Adding listener " + listener);
		listeners.add(listener);
	}

	@Override
	public synchronized void removeListener(ModuleServiceListener listener) {
		System.out.println("ModuleServiceImpl: Removing listener " + listener);
		listeners.remove(listener);
	}

	/**
	 * @return the logService
	 */
	public LogService getLogService() {
		return logService;
	}

	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}


}
