package edu.qh.ChaoSpringBoot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller // Bảo rằng đây là 1 lớp controller
 public class ChaoController
{
	//action method
	//URL gọi action này ???
	@GetMapping("/hi")
	public String xin_Chao(/*các tham số vào và ra*/)
	{
		return "helloView";
	}
	
	@GetMapping("/to")
	public String trangchu(ModelMap model)
	{
		model.addAttribute("tb","Hello");
		return "index";
	}
}
