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
package org.ws13.vaadin.osgi.dm.services;

import java.util.List;
import java.util.Map;

public interface ModuleService {

	void registerModule(Module module, Map<?,?> properties);
	void registerModule(Module module);
	
	void unregisterModule(Module module, Map<?,?> properties);
	void unregisterModule(Module module);
	List<Module> getModules();
	
	void addListener(ModuleServiceListener listener);
	
	void removeListener(ModuleServiceListener listener);
	
}
