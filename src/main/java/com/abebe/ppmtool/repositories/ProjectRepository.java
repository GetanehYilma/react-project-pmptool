package com.abebe.ppmtool.repositories;

import com.abebe.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAll();

    public Project findByProjectIdentifier(String projectId);
}
