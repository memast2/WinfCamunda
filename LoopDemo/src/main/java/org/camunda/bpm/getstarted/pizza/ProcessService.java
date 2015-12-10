package org.camunda.bpm.getstarted.pizza;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProcessService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void persistNewProcess(String task, String description) {
		ProcessEntity pe = new ProcessEntity();
		pe.setTask(task);
		pe.setDescription(description);
		em.persist(pe);
	}
	
	public void deleteProcess(String task){
		String sql = "DELETE FROM ProcessEntity p WHERE task = '" + task + "'";
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ProcessEntity> getCompleteProcessList() {
		Query q = em.createQuery("SELECT p FROM ProcessEntity p");
		return q.getResultList();
	}
}
