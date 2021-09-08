package org.ict.controller.di.classfile;

import org.springframework.stereotype.Component;

// 컴포넌트 스캔 방식
// 어노테이션 @Component, @Controller, @Repository, @Service 중 하나를
// 클래스명 위에 붙여주면 root-context(bean container)가 감지하는 방식입니다.
@Component
public class Singer {

	public void sing() {
		System.out.println("가수가 노래를 합니다.");
	}
}
