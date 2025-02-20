package com.project.sktaskmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sktaskmanager.data.TaskRepository;
import com.project.sktaskmanager.model.Task;
@Controller
@RequestMapping("/task")
public class TaskController {
   
  @Autowired
  private TaskRepository repository;
  
  @GetMapping
  public String getAll(Model model, @RequestParam(name = "search", required = false) String search){//, @PathVariable String search
    List<Task> taskList = new ArrayList<Task>();
    if(search!=null && !search.isEmpty())
      taskList = repository.findByTaskNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(search, search);//repository.findByNameOrCity(search);
    else 
      taskList= repository.findAll();
    model.addAttribute("taskList", taskList);
    return "index";
  }
  
  @GetMapping("/create")
  public String showAddingForm(Model model) {
    Task task = new Task();
    model.addAttribute("task", task);
    return "new_task";
  }
  
  @PostMapping("/create")
  public String createTask(@ModelAttribute("task") Task task) {
    repository.save(task);
    return "redirect:/task";
  }
  
  @GetMapping("/update/{id}")
  public String showUpdatingForm(@PathVariable("id") int id, Model model) {
    Task task = repository.findById(id).get(0);
    model.addAttribute("task", task);
    return "update_task";
  }
  
  @PostMapping ("/update/{id}")
  public String updateTask(@ModelAttribute("task") Task task) {
	  repository.save(task);
	  return "redirect:/task";
  }
  
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable int id) {
    repository.deleteById(id);
    return "redirect:/task";
  }
  }

	  

