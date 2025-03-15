package edu.qh.SB_TruyenDuLieuLogin_BMI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController
{
	@GetMapping("/login")
	public String showLogin()
	{
		return "login";
	}

	@PostMapping("/login")
	public String processLogin(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		if ("tk".equals(id) && "mk".equals(password))
		{
			return "bmi";
		} else
		{
			return "login";
		}
	}
}
