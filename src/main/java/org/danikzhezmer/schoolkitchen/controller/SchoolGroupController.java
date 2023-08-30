package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class SchoolGroupController {

    SchoolGroupRepository schoolGroupRepository;

    public SchoolGroupController(SchoolGroupRepository schoolGroupRepository) {
        this.schoolGroupRepository = schoolGroupRepository;
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
        return "redirect:/orders/new_order";
    }
}
