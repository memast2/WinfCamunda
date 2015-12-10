package org.camunda.bpm.getstarted.pizza;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
@ConversationScoped
public class PersistProcessPM implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessService service;
	
	public void persistProcess(DelegateExecution delegateExecution){
		
	    Map<String, Object> variables = delegateExecution.getVariables();
	    
	    if(variables.containsKey("delete")){
	    	String deleteName = (String)variables.get("delete");
	    	if(deleteName!=null){
	    		if(deleteName.length()>0){
	    	    	// we delete a task
	    			service.deleteProcess((String)variables.get("delete"));
	    		}
	    	}
	    }
	    
	    if(variables.containsKey("task") && variables.containsKey("description")){
	    	String taskName = (String)variables.get("task");
	    	String taskDescription = (String)variables.get("description");
	    	if(taskName!=null && taskDescription!=null){
	    		if(taskName.length()>0 && taskDescription.length()>0){
	    	    	// we create a task
	    			service.persistNewProcess(
	    					(String)variables.get("task"), 
	    					(String)variables.get("description")
	    			);
	    		}
	    	}
	    }
		
	}
	
	public List<ProcessEntity> getProcessList() {
		
		return service.getCompleteProcessList();
	}
	
	
}
