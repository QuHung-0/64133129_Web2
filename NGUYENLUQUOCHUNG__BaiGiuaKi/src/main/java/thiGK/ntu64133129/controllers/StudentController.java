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

	private List<Student> dsStudent;

	public StudentController()
	{
		dsStudent = new ArrayList<>();
		dsStudent.add(new Student(1, "Nguyễn Văn Hà", "Nhóm 1"));
		dsStudent.add(new Student(2, "Trần Thị Bảo", "Nhóm 2"));
		dsStudent.add(new Student(3, "Lê Văn Minh", "Nhóm 1"));
		dsStudent.add(new Student(4, "Lê Văn Minh", "Nhóm 2"));

	}

	@GetMapping("/student/all")
	public String listStudents(Model model)
	{
		model.addAttribute("dsStudent", dsStudent);
		return "studentList";
	}

	@GetMapping("/student/new")
	public String newStudentForm(Model model)
	{
		return "studentNew";
	}

	@PostMapping("/student/new")
	public String addNewStudent(@RequestParam("name") String name, @RequestParam("groupId") String groupId, Model model)
	{
		int newId = dsStudent.size() + 1;
		Student s = new Student(newId, name, groupId);
		dsStudent.add(s);
		return "redirect:/student/all";
	}

	@GetMapping("/student/view/{id}")
	public String viewStudent(@PathVariable("id") int id, Model model)
	{
		Student found = dsStudent.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
		model.addAttribute("student", found);
		return "studentView";
	}

	@GetMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable("id") int id)
	{
		dsStudent.removeIf(s -> s.getId() == id);
		return "redirect:/student/all";
	}

	@GetMapping("/student/edit/{id}")
	public String editStudentForm(@PathVariable("id") int id, Model model)
	{
		Student found = dsStudent.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
		model.addAttribute("student", found);
		return "studentEdit";
	}

	@PostMapping("/student/edit")
	public String editStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("groupId") String groupId)
	{
		Student found = dsStudent.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
		if (found != null)
		{
			found.setName(name);
			found.setGroupId(groupId);
		}
		return "redirect:/student/all";
	}
}
