package edu.qh.TongHopGK.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.qh.TongHopGK.model.SinhVien;

@Controller
public class HomeController
{
	private List<SinhVien> dssv = new ArrayList<>();

	public HomeController()
	{
		dssv.add(new SinhVien("SV001", "Trần Hải Đăng", 8.5));
		dssv.add(new SinhVien("SV002", "Lưu Thanh Hà", 7.8));
		dssv.add(new SinhVien("SV003", "Nguyễn Đắc Cẩn", 9.0));
	}

	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "Trang Chủ");
		return "index";
	}

	@GetMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title", "About");
		return "about";
	}

	@GetMapping("/studentList")
	public String studentList(Model model)
	{
		model.addAttribute("dssv", dssv);
		return "studentList";
	}

	@GetMapping("/addNew")
	public String addNewForm(Model model)
	{
		return "addnew";
	}

	@PostMapping("/addNew")
	public String addNewStudent(@RequestParam("mssv") String mssv, @RequestParam("hoTen") String hoTen,
			@RequestParam("diemTB") double diemTB, Model model)
	{

		SinhVien sv = new SinhVien(mssv, hoTen, diemTB);
		dssv.add(sv);
		return "redirect:/studentList";
	}
}
