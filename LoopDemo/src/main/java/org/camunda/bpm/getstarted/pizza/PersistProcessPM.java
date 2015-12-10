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
	private String currentUser = "test";

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
	    
	    if(variables.containsKey("user")){
	    	String userName = (String)variables.get("user");
	    	if(userName!=null){
	    		if(userName.length()>0){
		    	    currentUser = userName;
	    			
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
	
	public String getCurrentUser() {
		
		return currentUser;
	}
	
	public User[] getUsers() {
		 User[] users = new User[3];
	     users[0] = new User();
	     
	     users[0].setName("Hans");
	     users[0].setRole("Mitarbeiter");
		 
	     users[1] = new User();
	     
	     users[1].setName("Bernhard");
	     users[1].setRole("Mitarbeiter");
	     
	     users[2] = new User();
	     
	     users[2].setName("Angela");
	     users[2].setRole("Projektleiter");
	     return users;
	}
	
	
}
