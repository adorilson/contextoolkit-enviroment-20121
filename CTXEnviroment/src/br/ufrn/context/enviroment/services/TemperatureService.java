package br.ufrn.context.enviroment.services;

import br.ufrn.context.enviroment.event.EventHandler;
import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class TemperatureService extends Service {
	private EventHandler handler;
	public TemperatureService(final Widget widget, EventHandler handler){
		super(widget, "TemperatureService", 
                new FunctionDescriptions() {
                        { // constructor
                                // define function for the service
                                add(new FunctionDescription(
                                                "fireControl", 
                                                "Defines Temperature Controle and Fire Alarm", 
                                                widget.getNonConstantAttributes()));
                        }
                });
		this.handler=handler;
	}
	
	@Override
	public DataObject execute(ServiceInput serviceInput) {
		String status=serviceInput.getInput().getAttributeValue("status");
		handler.changeTemperature(status);
		return null;
	}

}
