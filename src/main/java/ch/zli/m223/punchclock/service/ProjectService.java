package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProjectService {
    @Inject
    private EntityManager entityManager;

    public ProjectService() {
    }

    @Transactional
    public Project createProject(Project project) {
        entityManager.persist(project);
        return project;
    }

    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }

    public Project getSingleProject(Long id) {
        return entityManager.find(Project.class, id);
    }

    public void delete(Long id) {
        Project entryToDelete = getSingleProject(id);
        entityManager.remove(entryToDelete);
    }

    public void updateProject(Project project) {
        entityManager.merge(project);
    }
}
