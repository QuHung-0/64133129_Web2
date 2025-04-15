package thiGK.ntu64133129.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thiGK.ntu64133129.model.Student;
import thiGK.ntu64133129.model.Topic;

@Controller
public class HomeControllers
{

	private List<Topic> topicList = new ArrayList<>();
	private List<Student> studentList = new ArrayList<>();

	public HomeControllers()
	{
		topicList.add(new Topic(1, "Topic 1", "diễn tả 1", "S001", "Loại 1"));
		topicList.add(new Topic(2, "Topic 2", "diễn tả 2", "S002", "Loại 2"));
		topicList.add(new Topic(3, "Topic 3", "diễn tả 3", "S003", "Loại 1"));
		topicList.add(new Topic(4, "Topic 4", "diễn tả 4", "S004", "Loại 1"));
		topicList.add(new Topic(5, "Topic 5", "diễn tả 5", "S005", "Loại 2"));
		topicList.add(new Topic(6, "Topic 6", "diễn tả 6", "S006", "Loại 2"));
		topicList.add(new Topic(7, "Topic 7", "diễn tả 7", "S007", "Loại 3"));

		studentList.add(new Student(1, "Nguyễn Thị Kim", "Nhóm 1"));
		studentList.add(new Student(2, "Hà Nam Tân", "Nhóm 2"));
		studentList.add(new Student(3, "Trần Văn Ánh", "Nhóm 1"));
		studentList.add(new Student(4, "Lê Thị Chánh", "Nhóm 2"));
		studentList.add(new Student(5, "Nguyễn Văn Chương", "Nhóm 3"));
		studentList.add(new Student(6, "Phạm Thị Đào", "Nhóm 1"));
		studentList.add(new Student(7, "Đỗ Văn Mận", "Nhóm 3"));
	}

	@GetMapping({ "/", "/dashboard" })
	public String dashboard(Model model)
	{
		return "dashboard";
	}

	@GetMapping("/topic/all")
	public String listTopics(Model model)
	{
		model.addAttribute("topics", topicList);
		return "topicList";
	}

	@GetMapping("/topic/new")
	public String newTopicForm()
	{
		return "topicNew";
	}

	@PostMapping("/topic/new")
	public String addTopic(@RequestParam("id") int id, @RequestParam("topicName") String topicName,
			@RequestParam("topicDescription") String topicDescription,
			@RequestParam("supervisorId") String supervisorId, @RequestParam("topicType") String topicType)
	{
		Topic topic = new Topic(id, topicName, topicDescription, supervisorId, topicType);
		topicList.add(topic);
		return "redirect:/topic/all";
	}

	@GetMapping("/topic/view/{id}")
	public String viewTopic(@PathVariable("id") int id, Model model)
	{
		Topic topic = topicList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		model.addAttribute("topic", topic);
		return "topicView";
	}

	@GetMapping("/topic/delete/{id}")
	public String deleteTopic(@PathVariable("id") int id)
	{
		topicList.removeIf(t -> t.getId() == id);
		return "redirect:/topic/all";
	}

	@GetMapping("/student/all")
	public String listStudents(Model model)
	{
		model.addAttribute("students", studentList);
		return "studentList";
	}

	@GetMapping("/student/new")
	public String newStudentForm()
	{
		return "studentNew";
	}

	@PostMapping("/student/new")
	public String addStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("groupId") String groupId)
	{
		Student student = new Student(id, name, groupId);
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

}
