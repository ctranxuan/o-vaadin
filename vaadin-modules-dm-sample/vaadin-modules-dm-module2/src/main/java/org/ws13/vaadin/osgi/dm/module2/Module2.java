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
package org.ws13.vaadin.osgi.dm.module2;

import org.ws13.vaadin.osgi.dm.services.Module;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class Module2 implements Module {

	public static class ModuleComponent extends CustomComponent {
		
		private static final long serialVersionUID = 2619946166525399922L;

		public ModuleComponent() {
			setCompositionRoot(new Label("Hello, this is Module 2"));
		}
			
	}
	
	public String getName() {
		return "Module 2";
	}
	
	public Component createComponent() {
		return new ModuleComponent();
	}
	
//	public void setModuleService(ModuleService service) {
//		System.out.println("Module2: registering with ModuleService");
//		service.registerModule(this);
//	}
//	
//	public void unsetModuleService(ModuleService service) {
//		System.out.println("Module2: unregistering with ModuleService");
//		service.unregisterModule(this);
//	}
}
