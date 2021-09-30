package org.ict.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ict.domain.TestVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {

	@RequestMapping("/hello")
	public String sayHello(){
		return "Hello Hello";
	}
	
	@RequestMapping("/sendVO")
	public TestVO sentTestVO() {
		TestVO testVO= new TestVO();
		
		testVO.setName("cho");
		testVO.setAge(21);
		testVO.setMno(1);
		return testVO;
	}
	@RequestMapping("/sendVOList")
	public List<TestVO> sendVOList(){
		List<TestVO> list =new ArrayList<>();
		for(int i=0;i<10;i++) {
			TestVO vo= new TestVO();
			vo.setMno(i);
			vo.setName(i+"cho");
			vo.setAge(20+i);
			list.add(vo);
			
		}return list;
	}
	@RequestMapping("/sendMap")
	public Map<Integer,TestVO>sendMap(){
		Map<Integer,TestVO> map=new HashMap<>();
		for(int i=0;i<10;i++) {
			TestVO vo= new TestVO();
			vo.setName("cho");
			vo.setMno(i);
			vo.setAge(50+i);
			map.put(i, vo);
	} 
		// map의 키값(왼쪽에 선언한것)은 중복된 값이 들어올수 없고
		// 들어온다면 가장 마지막에 넣은 하나만 남습니다.
		TestVO vo= new TestVO();
		vo.setName("0번바꾸기");
		vo.setMno(99);
		vo.setAge(999);
		map.put(0, vo);
		System.out.println(map);
		return map;
	}
	
	  @RequestMapping("/sendErrorAuth") 
	  public ResponseEntity<Void> sendListAuth(){
		  
		  // ResponseEntity는 생성자에 HttpStatus.코드번호
		  // 를 적어서 해당주소 접속시 어떤 접속코드를 사용자에게 넘길지 결정할수 있습니다.
		  return 
			  new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	  }
	  @RequestMapping("/sendErrorNot")
	  public ResponseEntity<List<TestVO>> sendListNot(){
		  List<TestVO> list =new ArrayList<>();
		  for(int i=0; i<10; i++) {
			  TestVO vo =new TestVO();
			  vo.setMno(i);
			  vo.setName(i+"cho");
			  vo.setAge(20+i);
			  list.add(vo);
		  }
		  return
				  new ResponseEntity<List<TestVO>>(list,HttpStatus.NOT_FOUND);
	  }
}
