package edu.qh.SB_TruyenDuLieuLogin_BMI.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BmiController
{

	@GetMapping("/bmi")
	public String showBmi()
	{
		return "bmi";
	}

	@PostMapping("/bmi")
	public String calculateBmi(HttpServletRequest request)
	{
		String heightStr = request.getParameter("height");
		String weightStr = request.getParameter("weight");

		double height = Double.parseDouble(heightStr);
		double weight = Double.parseDouble(weightStr);

		double bmi = weight / (height * height);
		
		String result;
		if (bmi < 17.5)
		{
			result = "Thiếu cân";
		} else if (bmi >= 17.5 && bmi < 23)
		{
			result = "Bình thường";
		} else if (bmi >= 23 && bmi < 28)
		{
			result = "Thừa cân";
		} else
		{
			result = "Béo phì";
		}

		request.setAttribute("bmi", bmi);
		request.setAttribute("result", result);

		return "bmi";
	}
}
