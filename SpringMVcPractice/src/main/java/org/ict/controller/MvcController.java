package org.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// 빈 컨테이너에 넣어주세요.(등록된 컨트롤러만 실제로 작동됨)

@Controller
public class MvcController {

	// 기본 주소(localhost:8181)뒤에 /goA를 붙이면 goA()매서드 실행
	@RequestMapping(value="/goA")
	// return 타입이 String인 경우 결과 페이지를 지정할 수 있음
	public String goA() {
		System.out.println("goA주소 접속 감지");
		// 결과 페이지는 views 폴더 아래의 A.jsp
		return "A";
	}
	
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB주소 감지");
		return "B";
	}
	
	// goC는 파라미터를 입력받을수 있도록 해보겠습니다.
	@RequestMapping(value="/goC")
	// 주소뒤 ?cNum=값 형태로 들어노는 값을 로직내cNum으로 처리합니다.
	public String goC(int cNum, Model model) {
		 System.out.println("주소로 전달받은 값 : "+cNum);
		 // 전달받은 cNum을 C.jsp에 출력하는 로직작성
		 model.addAttribute("cNum",cNum);
		 return "C";
		
	}
	// goD는 requestParam을 이용해 변수명과 받는 이름이 일치하지 않게 해보겠습니다.
	@RequestMapping(value="/goD")
	// @@RequestParam("대체이름")은 변수 왼쪽에 선언
	// 이런게되면 적힌 변수명대신 대체이름으로 치환해 받아옴
	public String goD(@RequestParam("d") int dNum, Model model) {
		System.out.println("d 변수명으로 받은게 dNum에 저장  : "+dNum);
		model.addAttribute("dNum",dNum);
		return "D";
	}
	
	// cToF 매서드를 만들겠습니다.
	// 섭씨 온도를 입력받아 화씨 온도로 바꿔서 출력해주는 로직입니다.
	// (화씨 - 32 / 1.8) = 섭씨
	
	// 폼에서 post방식으로 제출했을때에만 결과 페이지로 넘어오도록 설계
	@RequestMapping(value="/ctof", method=RequestMethod.POST)
	public String cToF(@RequestParam("cel") int cel, Model model) {
		double S = (double) ((cel - 32)/1.8);
		System.out.println("섭씨 : " +S);
		model.addAttribute("S",S);
		model.addAttribute("cel",cel);
		return "ctof";
		
	}
	
	// 폼으로 연결하는 매서드도 만들겠습니다.
	// 폼과 결과페이지가 같은 주소를 공유하게 하기위해서 폼쪽을 겟방식 접근허용
	@RequestMapping(value="/ctof", method=RequestMethod.GET)
	public String cToFForm() {
		// ctofform을 이용해 섭씨온도를 입력하고 제출버튼을 누르면
		// 결과값이 나오는 로직을 작성해주세요
		// input 태그의 name 속성은 cel로 주시면 되고
		// action은 value에 적힌 주소값으로 넘겨주시면 됩니다.
		
		
		return "ctofform";
	}
	
	// 위와 같은 방식으로 bmi측정 페이지를 만들어 보겠습니다.
	// 폼과 결과페이지로 구성해주면 되고
	// bmi공식은 체중 / (키cm^2) 으로 나오는 결과입니다.
	
	@RequestMapping(value="/bmi",method=RequestMethod.POST)
	public String Bmi(int height,int weight, Model model) {
		double m =height/100.0;
		double bmi=weight/(m*m);
		model.addAttribute("bmi",bmi);
		model.addAttribute("height",height);
		model.addAttribute("weight",weight);
		
	return "bmiresult";
	}
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String bmiForm() {
		return "bmiform";
	}
	
	// PathVariable 을 이용하면 url 패턴만으로도 특정 파라미터를 받아올 수 있습니다.
	// rest방식으로 url을 처리할떄 주로 사용하는 방식임
	// /pathtest/숫자 중 숫자 위치에 온것은 page라는 변수값으로 간주
	@RequestMapping(value="/pathtest/{page}")
	// int page 왼쪽에 @PathVariable을 붙여서 처리해야 연동됨
	public String pathTest(@PathVariable int page, Model model) {
		// 받아온 page 변수를 path.jsp로 보내주세요.
		// path.jsp에는 {page} 페이지 조회중입니다 라는 문장이 뜨게해주세요
		model.addAttribute("page",page);
		return "path";
		
	}
	
	// ctof 로직을 PathVariable 을 적용해서 만들어주세요
	// ctofpv.jsp에 결과가 나오면된다.
	// 섭씨온도cel 을 url 일부로 받도록 처리
	@RequestMapping(value="/ctof/{cel}")
	public String cToFPv(@PathVariable int cel,Model model) {
		// cel 변수를 받아서 화씨로 고쳐 faren에 저장
		double faren = cel*1.8+32;
		// .jsp(view)에 저장
		model.addAttribute("cel",cel);
		model.addAttribute("faren",faren);
		return "ctofpv";
	}
	
	// void 타입 컨트롤러의 특징
	// void 타입은 return구문을 사용할수 없는만큼
	// view파일의 이름을 그냥 매서드이름.jsp로 자동 지정해버립니다.
	// 간단한 작성은 void 타입으로 해도 되지만 메서드명에 제약이 생겨 잘 안씁니다.
	@RequestMapping(value="/voidreturn")
	public void voidTest( int vt,Model model) {
		System.out.println("void 컨트롤러는 리턴구문이 필요없습니다.");
		// 1. 파라미터를 아무거나 받아오게 임의로 설정해주기
		// 2. 현 매서드에 맞는 view파일을 생성하기
		// 3. 1에서 얻어온 파라미터를 2 에 출력되도록설정
		model.addAttribute("vt",vt);
		
	}
	
	
}
