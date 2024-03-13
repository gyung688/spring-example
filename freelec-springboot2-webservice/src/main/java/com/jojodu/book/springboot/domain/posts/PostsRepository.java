package com.jojodu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    // 보통 Mybatis 등에서 Dao라고 불리는 DB Layer 접근자
    // JPA에서 Repository라고 부르며 인터페이스로 생성한다.
    // JpaRepository<Entity클래서, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.

    // 규모 있는 프로젝트에서는 Entity클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용한다.
    // querydsl, jooq, MyBatis 등을 사용해 조회를 하고 등록/수정/삭제는 SpringDataJpa를 통해 진행.
    // querydsl 추천 : 메소드를 기반으로 쿼리를 생성해서 오타가 존재하지 않는 컬럼명을 명시하면 IDE에서 자동으로 검출됨.


    //SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성해도 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
