package edu.ntu.hung.controller;

import edu.ntu.hung.entity.MonHoc;
import edu.ntu.hung.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/giaovien/subject")
public class SubjectController
{

	@Autowired
	private MonHocRepository monHocRepo;

	// 1. Xem danh sách tất cả môn học (GET)
	@GetMapping("/list")
	public String listSubjects(Model model)
	{
		List<MonHoc> dsMon = monHocRepo.findAll();
		model.addAttribute("dsMonHoc", dsMon);
		// templates/giaovien/subject_list.html
		return "giaovien/subject_list";
	}

	// 2. Hiển thị form TẠO mới hoặc SỬA (GET)
	@GetMapping("/form")
	public String showSubjectForm(@RequestParam(value = "monHocId", required = false) Integer monHocId, Model model)
	{
		if (monHocId == null)
		{
			model.addAttribute("monHoc", new MonHoc());
		} else
		{
			MonHoc mh = monHocRepo.findById(monHocId).orElse(null);
			if (mh == null)
			{
				return "redirect:/giaovien/subject/list";
			}
			model.addAttribute("monHoc", mh);
		}
		return "giaovien/subject_form";
	}

	// 3. Xử lý LƯU (CREATE or UPDATE) (POST)
	@PostMapping("/save")
	public String saveSubject(@ModelAttribute("monHoc") MonHoc monHoc)
	{
		monHocRepo.save(monHoc);
		return "redirect:/giaovien/subject/list";
	}

	// 4. Xóa môn học (GET)
	@GetMapping("/delete")
	public String deleteSubject(@RequestParam("monHocId") Integer monHocId)
	{
		monHocRepo.deleteById(monHocId);
		return "redirect:/giaovien/subject/list";
	}
}
