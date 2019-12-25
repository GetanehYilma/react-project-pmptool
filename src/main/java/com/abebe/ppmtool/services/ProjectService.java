package com.abebe.ppmtool.services;

import com.abebe.ppmtool.domain.Project;
import com.abebe.ppmtool.exception.ProjectIdException;
import com.abebe.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex){
            throw  new ProjectIdException("Project Id: " + project.getProjectIdentifier() + " already exists.");
        }

    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw  new ProjectIdException("Project Id: " + "'" + projectId +"'" + " doesn't exist.");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectById(String projectId){
//        Project project = projectRepository.findByProjectIdentifier(projectId);
        Project project =findProjectByIdentifier(projectId);
        if (project == null){
            throw  new ProjectIdException("Project: '" + projectId + " cannot be deleted. It doesn't exist.");
        }
        projectRepository.delete(project);
    }
}
