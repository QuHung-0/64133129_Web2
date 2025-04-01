package thiGK.ntu64133129.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thiGK.ntu64133129.model.Topic;

@Controller
public class TopicController
{
	private List<Topic> topicList = new ArrayList<>();
	private int nextTopicId = 1;

	public TopicController()
	{
		topicList.add(new Topic(nextTopicId++, "Topic 1", "dien ta 1", "S001", "Loai 1"));
		topicList.add(new Topic(nextTopicId++, "Topic 2", "dien tan 2", "S002", "Loai 2"));
	}

	@GetMapping("/topic/all")
	public String listTopics(Model model)
	{
		model.addAttribute("topics", topicList);
		return "topicAll";
	}

	@GetMapping("/topic/new")
	public String newTopicForm()
	{
		return "topicNew";
	}

	@PostMapping("/topic/new")
	public String addTopic(@RequestParam("topicName") String topicName,
			@RequestParam("topicDescription") String topicDescription,
			@RequestParam("supervisorId") String supervisorId, @RequestParam("topicType") String topicType)
	{
		Topic topic = new Topic(nextTopicId++, topicName, topicDescription, supervisorId, topicType);
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

	@GetMapping("/topic/edit/{id}")
	public String editTopicForm(@PathVariable("id") int id, Model model)
	{
		Topic topic = topicList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		if (topic == null)
		{
			return "redirect:/topic/all";
		}
		model.addAttribute("topic", topic);
		return "topicEdit";
	}

	@PostMapping("/topic/edit")
	public String editTopic(@RequestParam("id") int id, @RequestParam("topicName") String topicName,
			@RequestParam("topicDescription") String topicDescription,
			@RequestParam("supervisorId") String supervisorId, @RequestParam("topicType") String topicType)
	{
		Topic topic = topicList.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		if (topic != null)
		{
			topic.setTopicName(topicName);
			topic.setTopicDescription(topicDescription);
			topic.setSupervisorId(supervisorId);
			topic.setTopicType(topicType);
		}
		return "redirect:/topic/all";
	}
}
