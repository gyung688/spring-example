package com.jojodu.book.springboot.domain.posts;

import com.jojodu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 어노테이션 순서 : 주요 어노테이션을 클래스에 가깝게 둔다.
@Getter
@NoArgsConstructor // 기본 생성자 자동 추가, public Posts(){}와 같은 효과
@Entity // JPA의 어노테이션. 테이블과 링크될 클래스임을 나타냄.
        // 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭한다. SalesManager.java > sales_manager table
public class Posts extends BaseTimeEntity {

    // Entity 클래스는 DB와 맞닿은 핵심 플래스이다. 테이블 생성 및 스키마 변경 시 사용된다.
    // 화면 변경은 아주 사소한 기능인데 이를 위해 테이블과 관련된 Entity 클래스를 변경하는 것은 너무 큰 변경이다.
    // View Layer와 DB Layer의 역할 분리를 철저하게 하는 것이 좋다.
    // **꼭 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

//    @Id // 해당 테이블의 PK필드를 나타낸다.
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙을 나타낸다.
//                                                        // 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 autho_increment가 된다.
//    private Long id;
//
//    @Column(length = 500, nullable = false) // 테이블의 컬럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 됨.
//                                            // 사용 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
//                                            // 문자열 사이즈를 늘리거나 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용된다.
//    private String title;
//
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String content;
//
//    private String author;
//
//    @Builder // 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
//    public Posts(String title, String content, String author){
//        this.title = title;
//        this.content = content;
//        this.author = author;
//    }
}
