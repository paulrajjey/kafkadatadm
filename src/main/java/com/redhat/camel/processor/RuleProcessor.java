package com.redhat.camel.processor;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.albertsons.model.Customer;


public class RuleProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		

		Object inBody = exchange.getIn().getBody();
		if((inBody instanceof Customer)){
			Customer cc = (Customer)inBody;
			System.out.println("fact before rule fired" + cc.toString());
		}else {
			System.out.println("fact before --- rule fired" + inBody.toString());

		}

		Object results = fireAllRules(inBody);
		
		if((results instanceof Customer)){
			Customer c = (Customer)results;
			System.out.println("fact after rule fired" + c.toString());
		}else {
			System.out.println("fact after --- rule fired" + results.toString());

		}
		
		
		exchange.getOut().setBody(results);

	}
	public Object fireAllRules(Object facts) {
		
		RuleExecuter executor = RuleExecuter.getRuleExecuter();
		KieSession kieSession  = executor.getKieSession();
		//for (Object input : facts) {
			
		FactHandle fhandle = kieSession.insert(facts);
			
		//}
		
		kieSession.fireAllRules();
		kieSession.delete(fhandle);		
		return facts;
	}


}
