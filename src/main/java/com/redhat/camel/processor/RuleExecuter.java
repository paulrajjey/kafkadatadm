package com.redhat.camel.processor;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleExecuter {
	
	private   KieServices kieServices ;
  
    private   KieContainer kieContainer ;
    private   KieSession kieSession ;
    private   KieScanner kieScanner ;
    private  static  RuleExecuter ruleExecuter ;
    
    private RuleExecuter() {
    	 kieServices = KieServices.Factory.get();
    	ReleaseId releaseId = kieServices.newReleaseId("com.albertsons", "dataquality", "LATEST");
    	this.kieServices =  KieServices.Factory.get();
    	this.kieContainer = kieServices.newKieContainer(releaseId);
    	this.kieSession = kieContainer.newKieSession();
    	this.kieScanner = kieServices.newKieScanner(kieContainer);
    	kieScanner.start(10000);
    }
    
    public static RuleExecuter  getRuleExecuter() {
    	if(ruleExecuter == null) {
    		ruleExecuter = new RuleExecuter();
    		ruleExecuter.kieSession.addEventListener(new AgendaEventListener() {
				
				@Override
				public void matchCreated(MatchCreatedEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void matchCancelled(MatchCancelledEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeMatchFired(BeforeMatchFiredEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void agendaGroupPushed(AgendaGroupPushedEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterMatchFired(AfterMatchFiredEvent event) {
					System.out.println("Rule fired " + event.getMatch().getRule().getName());
					System.out.println("Rule fired meta data " + event.getMatch().getRule().getMetaData().toString());
					
				}
			});
    		
    	}
    	return ruleExecuter;
    	
    }

	public KieSession getKieSession() {
		
		return kieSession;
	}

	
   

}
