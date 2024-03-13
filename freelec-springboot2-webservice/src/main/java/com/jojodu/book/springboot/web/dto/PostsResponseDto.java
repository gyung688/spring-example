package com.jojodu.book.springboot.web.dto;

import com.jojodu.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    // Entity의 필드 중 일부만 사용하므로 생성자로 Enitity를 받아 필드에 넣는다.

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }


}
