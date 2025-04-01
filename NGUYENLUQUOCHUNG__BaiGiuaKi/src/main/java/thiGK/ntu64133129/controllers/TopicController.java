package thiGK.ntu64133129.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thiGK.ntu64133129.model.Topic;

@Controller
public class TopicController
{

	private List<Topic> dsTopic;

	public TopicController()
	{
		dsTopic = new ArrayList<>();
		dsTopic.add(new Topic(1, "Lập trình Java", "Học lập trình Java nâng cao", "S001", "Lý thuyết"));
		dsTopic.add(new Topic(2, "Phát triển Web", "Xây dựng website với Spring Boot", "S002", "Thực hành"));
		dsTopic.add(new Topic(3, "Machine Learning", "Ứng dụng ML trong dự án", "S003", "Nghiên cứu"));
	}

	@GetMapping("/topic/all")
	public String listTopics(Model model)
	{
		model.addAttribute("dsTopic", dsTopic);
		return "topicList";
	}

	@GetMapping("/topic/new")
	public String newTopicForm(Model model)
	{
		return "topicNew";
	}

	@PostMapping("/topic/new")
	public String addNewTopic(@RequestParam("topicName") String topicName,
			@RequestParam("topicDescription") String topicDescription,
			@RequestParam("supervisorId") String supervisorId, @RequestParam("topicType") String topicType, Model model)
	{
		int newId = dsTopic.size() + 1;
		Topic t = new Topic(newId, topicName, topicDescription, supervisorId, topicType);
		dsTopic.add(t);
		return "redirect:/topic/all";
	}

	@GetMapping("/topic/view/{id}")
	public String viewTopic(@PathVariable("id") int id, Model model)
	{
		Topic found = dsTopic.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		model.addAttribute("topic", found);
		return "topicView";
	}

	@GetMapping("/topic/delete/{id}")
	public String deleteTopic(@PathVariable("id") int id)
	{
		dsTopic.removeIf(t -> t.getId() == id);
		return "redirect:/topic/all";
	}

	@GetMapping("/topic/edit/{id}")
	public String editTopicForm(@PathVariable("id") int id, Model model)
	{
		Topic found = dsTopic.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		model.addAttribute("topic", found);
		return "topicEdit";
	}

	@PostMapping("/topic/edit")
	public String editTopic(@RequestParam("id") int id, @RequestParam("topicName") String topicName,
			@RequestParam("topicDescription") String topicDescription,
			@RequestParam("supervisorId") String supervisorId, @RequestParam("topicType") String topicType)
	{
		Topic found = dsTopic.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		if (found != null)
		{
			found.setTopicName(topicName);
			found.setTopicDescription(topicDescription);
			found.setSupervisorId(supervisorId);
			found.setTopicType(topicType);
		}
		return "redirect:/topic/all";
	}
}
