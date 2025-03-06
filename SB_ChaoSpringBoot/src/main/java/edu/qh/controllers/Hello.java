package edu.qh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello
{
	@GetMapping("/chao")
	public String hh(ModelMap m) 
	{
		//Gói dữ liệu vao biến m, để đưa sang view hiển thị
		m.addAttribute("tb", "Dữ liệu thông báo xin chào");
		
		return "helloView";
	}
	
}