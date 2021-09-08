package org.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Broadcast {
	
	private Stage stage;
	
	// @Autowired를 이용한 주입은 생성자에도 적용할 수 있습니다.
	@Autowired
	public Broadcast(Stage stage) {
		this.stage = stage;
	}
	
	public void broadcast() {
		System.out.print("방송 송출용 ");
		stage.perform();
	}
}
