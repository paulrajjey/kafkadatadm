package com.redhat.camel.processor;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.server.api.marshalling.Marshaller;
import org.kie.server.api.marshalling.MarshallerFactory;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;


import com.albertsons.model.Customer;

public class DecisionRestClientProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		 //Customer customer = new Customer();
		 
		 Object inBody = exchange.getIn().getBody();
		
		 List<Command<?>> cmds = new ArrayList<Command<?>>();
	        KieCommands commands = KieServices.Factory.get().getCommands();
	        cmds.add(commands.newInsert(inBody ,"result" ));
	        cmds.add(commands.newFireAllRules());
	        //cmds.add(commands.newQuery("greetings", "get greeting"));
	        BatchExecutionCommand command = commands.newBatchExecution(cmds);
	        
	        Marshaller marshaller = MarshallerFactory.getMarshaller( MarshallingFormat.JSON, getClass().getClassLoader() );
	        String xStreamXml = marshaller.marshall( command );
	        System.out.println("\t######### Rules request"  + xStreamXml);

			exchange.getOut().setBody(xStreamXml);

		
	}
	
	
	
   

}
