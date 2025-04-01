package thiGK.ntu64133129.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thiGK.ntu64133129.model.Student;

@Controller
public class StudentController
{
	private List<Student> studentList = new ArrayList<>();
	private int nextStudentId = 1;

	public StudentController()
	{
		studentList.add(new Student(nextStudentId++, "Nguyễn Thị Kim", "Nhóm 1"));
		studentList.add(new Student(nextStudentId++, "Hà Nam Tân", "Nhóm 2"));
	}

	@GetMapping("/student/all")
	public String listStudents(Model model)
	{
		model.addAttribute("students", studentList);
		return "studentAll";
	}

	@GetMapping("/student/new")
	public String newStudentForm()
	{
		return "studentNew";
	}

	@PostMapping("/student/new")
	public String addStudent(@RequestParam("name") String name, @RequestParam("groupId") String groupId)
	{
		Student student = new Student(nextStudentId++, name, groupId);
		studentList.add(student);
		return "redirect:/student/all";
	}

	@GetMapping("/student/view/{id}")
	public String viewStudent(@PathVariable("id") int id, Model model)
	{
		Student student = studentList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
		model.addAttribute("student", student);
		return "studentView";
	}

	@GetMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable("id") int id)
	{
		studentList.removeIf(s -> s.getId() == id);
		return "redirect:/student/all";
	}

	@GetMapping("/student/edit/{id}")
	public String editStudentForm(@PathVariable("id") int id, Model model)
	{
		Student student = studentList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
		if (student == null)
		{
			return "redirect:/student/all";
		}
		model.addAttribute("student", student);
		return "studentEdit";
	}

	@PostMapping("/student/edit")
	public String editStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("groupId") String groupId)
	{
		Student student = studentList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
		if (student != null)
		{
			student.setName(name);
			student.setGroupId(groupId);
		}
		return "redirect:/student/all";
	}
}
