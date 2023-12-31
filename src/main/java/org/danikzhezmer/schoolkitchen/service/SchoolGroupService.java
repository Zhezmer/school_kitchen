package org.danikzhezmer.schoolkitchen.service;

import org.danikzhezmer.schoolkitchen.entity.SchoolGroup;
import org.danikzhezmer.schoolkitchen.exception.EntityNotFoundException;
import org.danikzhezmer.schoolkitchen.repository.SchoolGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolGroupService {
    private final SchoolGroupRepository schoolGroupRepository;

    public SchoolGroupService(SchoolGroupRepository schoolGroupRepository) {
        this.schoolGroupRepository = schoolGroupRepository;
    }

    public SchoolGroup findById(Long id) {
        return schoolGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Group with current id not found " + id));
    }

    public List<SchoolGroup> findAll(){
        return schoolGroupRepository.findAll();
    }

    public SchoolGroup save(SchoolGroup group){
        return schoolGroupRepository.save(group);
    }

    public void deleteById(Long id){
        schoolGroupRepository.deleteById(id);
    }

    public List<String> findGroup(){
        return schoolGroupRepository.findAll().stream().map(SchoolGroup::getName).collect(Collectors.toList());
    }
}
