package com.example.switchyard.switchyard_example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public interface DummyRest {
	@GET
	@Path("/sayHello/{fullName}")
	public String sayHello(@PathParam("fullName") String fullName);
}
