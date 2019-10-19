package org.examples;

import org.examples.domain.AppMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@RequestMapping("/mode2Str")
	public String processUsingModeAsRequestParam(@RequestParam(name="mode") AppMode mode)
	{
		//
		return "mode2Str";
	}
	@RequestMapping("/mode2Str/{mode}")
	public String processUsingModeAsPathVariable(@PathVariable(name="mode") AppMode mode)
	{
		//
		return "mode2Str";
	}
}
