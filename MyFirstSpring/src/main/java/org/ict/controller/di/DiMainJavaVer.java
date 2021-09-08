package org.ict.controller.di;

import org.ict.controller.di.classfile.Broadcast;
import org.ict.controller.di.classfile.Singer;
import org.ict.controller.di.classfile.Stage;

public class DiMainJavaVer {

	public static void main(String[] args) {
		//1. 가수 객체를 main에서 생성해 기능을 호출해주세요.
		Singer singer = new Singer();
		singer.sing();
		
		//2. 무대 객체를 생성해주신 다음, 기능을 호출해주세요.
		// 무대객체는 생성시 반드시 singer가 먼저 있어야 하므로
		// 무대는 singer에 의존합니다.
		Stage stage = new Stage(singer);
		stage.perform();
		
		// 3. 방송 무대를 송출하는 기능을 호출해주세요.
		Broadcast broadcast = new Broadcast(stage);
		broadcast.broadcast();
		
	}

}
