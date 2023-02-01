package com.jojodu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication // 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
public class Application {
    // 앞으로 만들 프로젝트의 메인 클래스.
    // @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트 최상단에 위치해야한다.
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS실행 = 톰캣 설치 불필요, 스프링 부트로 만들어진 Jar파일로 실행하면 됨
        // 내장 WAS의 장점은 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있는 것임 (WAS종류,버전,설정 일치 할 필요없어짐)
    }
}

// 템플릿 엔진 : 지정된 템플릿 양식 + 데이터 => HTML문서를 출력하는 소프트웨어
// 예시) JSP, Freemarker, 리액트, 뷰
// JSP, Freemarker => 서버 템플릿 엔진
// 리액트, 뷰 => 클라이언트 템플릿 엔진
// JSP는 서버 템플릿 엔진은 아니지만, View의 역할만 하도록 구성할 때는 템플릿 엔진으로써 사용할 수 있다. 이 경우 Spring + JSP로 사용한 경우로 보면 됨.
// 서버 템플릿 엔진을 이용한 화면 생성은 서버에서 java코드로 문자열을 만든 뒤 이 문자열을 HTML로 변환하여 브라우저로 전달한다.
// 자바스크립트는 브라우저 위에서 작동한다. 그래서 자바코드가 실행된 후 자바스크립트 코드가 실행된다.
// 뷰나 리액트를 이용한 SPA는 브라우저에서 화면을 생성한다. 서버에서는 Json 혹은 XML 형식의 데이터만 전달하고 클라이언트에서 조립한다.
