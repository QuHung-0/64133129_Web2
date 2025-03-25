package edu.qh.TongHopGK.controllers;

import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.qh.TongHopGK.model.SinhVien;


public class HomeController
{
	ArrayList<SinhVien> dsSV = new ArrayList<>();
	
	@GetMapping("/")
	public String home(Model model)
	{
		return "index";
	}

	@GetMapping("/about")
	public String about()
	{
		return "about";
	}

	@GetMapping("/List")
	public String List()
	{
		return "List";
	}

	@GetMapping("/Addnew")
	public String Addnew()
	{
		return "Addnew";
	}

}
