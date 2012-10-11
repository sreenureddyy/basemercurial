/**
 * 
 */
package com.sree.base.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.base.service.jms.IBasePublisher;

/**
 * @author Sree
 *
 */
@Component("sendMessageBean")
@Scope(value = "request")
public class SendMessageBean extends BaseBean{
	@Autowired
	private IBasePublisher basePublisher;

	public IBasePublisher getBasePublisher() {
		return basePublisher;
	}

	public void setBasePublisher(IBasePublisher basePublisher) {
		this.basePublisher = basePublisher;
	}
	
	public void sendMessage() throws Exception{
		basePublisher.publish("AlertSubscriber", "Heloooooooooooo Sreeeeeeeeee.........");
	}
}
