package br.ufrn.context.enviroment.services;

import br.ufrn.context.enviroment.event.EventHandler;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class PollutionService extends Service {
	private EventHandler handler;
	public PollutionService(final Widget widget, EventHandler handler){
		super(widget, "PollutionService", 
                new FunctionDescriptions() {
                        { // constructor
                                // define function for the service
                                add(new FunctionDescription(
                                                "pollutionControl", 
                                                "Defines Pollution Control", 
                                                widget.getNonConstantAttributes()));
                        }
                });
		this.handler=handler;
		System.out.println("PollutionService.new executado");
		
	}
	
	@Override
	public DataObject execute(ServiceInput serviceInput) {
		System.out.println("PollutionService.execute");
		String status=serviceInput.getInput().getAttributeValue("status");
		handler.changePollutionLevel(status);
		return null;
	}

}
