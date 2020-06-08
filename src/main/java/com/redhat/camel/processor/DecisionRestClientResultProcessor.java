package com.redhat.camel.processor;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
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

public class DecisionRestClientResultProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		 //Customer customer = new Customer();
			String inBody = exchange.getIn().getBody(String.class);
	       System.out.println("\t######### rest input" + inBody);


	        
	        Marshaller marshaller = MarshallerFactory.getMarshaller( MarshallingFormat.JSON, getClass().getClassLoader() );
	        
	        ServiceResponse<ExecutionResults>  serviceResponse  = marshaller.unmarshall( inBody , ServiceResponse.class);
	        Customer custoner = (Customer)serviceResponse.getResult().getValue("result");
	        
	       // String xStreamXml = marshaller.unmarshall( inBody , ServiceResponse.class);
		       System.out.println("\t######### rest result");

	       System.out.println(custoner.toString());
	       
	       
	       System.out.println("\t######### rest result"  );


			exchange.getOut().setBody( custoner.toString());

		
	}
	
	
	
   

}
