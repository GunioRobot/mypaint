package br.com.mypaint.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class IndexController {

	private final Result result;

	private Validator validator;

	public IndexController(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
	}

	@Path("/")
	public void index() {
		result.include("vraptor", "VRaptor is working...");
	}

}
