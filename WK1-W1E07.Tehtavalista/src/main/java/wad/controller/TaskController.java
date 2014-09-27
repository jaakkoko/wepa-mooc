package wad.controller;

import wad.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    
    @RequestMapping(method=RequestMethod.GET)
    public String getTasks(Model model){
        model.addAttribute("tasks",taskRepository.findAll());
        return "/WEB-INF/views/page.jsp";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String addTask(String name, boolean done){
        if (name!=null && !name.isEmpty()){
            Task task = new Task();
            task.setName(name);
            task.setDone(done);
            taskRepository.save(task);
        }
        return "redirect:/tasks";
    }
    
    @RequestMapping(value="/{id}/delete", method=RequestMethod.POST)
    public String deleteTask(@PathVariable String id){
        if(id!=null){
            long task_id = Long.parseLong(id);
            taskRepository.delete(task_id);
        }
        return "redirect:/tasks";
    }
    
    @Transactional
    @RequestMapping(value="/{id}/done", method=RequestMethod.POST)
    public String setDone(@PathVariable String id){
        if(id!=null){
            long task_id = Long.parseLong(id);
            Task task = taskRepository.findOne(task_id);
            task.setDone(true);
        }
        return "redirect:/tasks";
    } 
}
