package org.danikzhezmer.schoolkitchen.controller;


import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.service.SchoolGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("groups")
public class SchoolGroupController {

  private final SchoolGroupService schoolGroupService;

    public SchoolGroupController(SchoolGroupService schoolGroupService) {
        this.schoolGroupService = schoolGroupService;
    }

    @GetMapping("/{id}")
    public String getGroup(@PathVariable("id") Long id, Model model) {
        model.addAttribute("group", schoolGroupService.findById(id));
        return "group/group_card";
    }

    @GetMapping
    public String getGroupList(Model model) {
        model.addAttribute("groups", schoolGroupService.findAll());
        return "group/group_list";
    }
    @GetMapping("/new_group")
    public String newGroupForm(Model model) {
        model.addAttribute("group", new SchoolGroup());
        model.addAttribute("listOfGroups", schoolGroupService.findGroup());
        return "group/new_group";
    }

    @PostMapping("/new_group")
    public String submitForm(@ModelAttribute SchoolGroup group, Model model) {
        model.addAttribute("group", group);
        schoolGroupService.save(group);
        return "redirect:/groups";
    }
    @GetMapping("/{groupId}/delete")
    public String deleteGroup(@PathVariable Long groupId) {
       schoolGroupService.deleteById(groupId);
        return "redirect:/groups";
    }
}
