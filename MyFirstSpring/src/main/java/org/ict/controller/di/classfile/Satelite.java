package org.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Satelite {
	@Autowired
	private Broadcast broadcast;
	public Satelite(Broadcast broadcast) {
		this.broadcast=broadcast;
	}
	
	public void satelite(){
		System.out.print("[[위성방송 송출중]]");
		broadcast.broadcast();
	}
	
}
