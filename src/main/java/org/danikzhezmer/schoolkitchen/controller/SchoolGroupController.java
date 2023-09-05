package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.danikzhezmer.schoolkitchen.service.SchoolGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
        return "/group/group_card";
    }

    @GetMapping
    public String getGroupList(Model model) {
        model.addAttribute("groups", schoolGroupService.findAll());
        return "group/group_list";
    }
    @GetMapping("/new_group")
    public String newGroupForm(Model model) {
        model.addAttribute("group", new SchoolGroup());
        List<String> listOfGroups = Arrays.asList("Group1", "Group2", "Group3");
        model.addAttribute("listOfGroups", listOfGroups);
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
