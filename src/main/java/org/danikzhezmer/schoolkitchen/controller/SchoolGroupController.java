package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Product;
import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("groups")
public class SchoolGroupController {

    SchoolGroupRepository schoolGroupRepository;

    public SchoolGroupController(SchoolGroupRepository schoolGroupRepository) {
        this.schoolGroupRepository = schoolGroupRepository;
    }

    @GetMapping("/{id}")
    public String getGroup(@PathVariable("id") Long id, Model model) {
        SchoolGroup group = schoolGroupRepository.findById(id).orElse(null);
        model.addAttribute("group", group);
        return "/group/group_card";
    }

    @GetMapping
    public String getGroupList(Model model) {
        List<SchoolGroup> groups = schoolGroupRepository.findAll();
        model.addAttribute("groups", groups);
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
        schoolGroupRepository.save(group);
        return "redirect:/groups";
    }
    @GetMapping("/{groupId}/delete")
    public String deleteGroup(@PathVariable Long groupId) {
       schoolGroupRepository.deleteById(groupId);
        return "redirect:/groups";
    }
}
