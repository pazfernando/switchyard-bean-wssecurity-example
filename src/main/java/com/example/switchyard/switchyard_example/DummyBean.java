package com.example.switchyard.switchyard_example;

import org.switchyard.component.bean.Service;

@Service(Dummy.class)
public class DummyBean implements Dummy {

	@Override
	public String sayHello(DummyGuy dummy) {
		return "Hello " + dummy.getName() + " " + dummy.getLastName();
	}

	@Override
	public String sayBye(DummyGuy dummy) {
		return "Hello " + dummy.getName() + " " + dummy.getLastName();
	}

}
