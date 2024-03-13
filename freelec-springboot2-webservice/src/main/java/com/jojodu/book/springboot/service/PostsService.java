package com.jojodu.book.springboot.service;

import com.jojodu.book.springboot.domain.posts.Posts;
import com.jojodu.book.springboot.domain.posts.PostsRepository;
import com.jojodu.book.springboot.web.dto.PostsListResponseDto;
import com.jojodu.book.springboot.web.dto.PostsResponseDto;
import com.jojodu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojodu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    // Bean을 주입받을 때 @Autowired는 권장하지 않는다.
    // 가장 권장하는 방식이 생성자로 주입받는 방식.
    // 생성자는 @RequiredArgsConstructor에서 해결해준다.
    // final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해준다.
    // 생성자를 안쓰고 롬복을 쓰는 이유는 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함임.
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" +id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        // JPA Entity Manager가 활성화된 상태로 트랜잭션 안에서 디비에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
        // 이 상태에서 해당 데이터 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
        // 즉 Entity 객체의 값만 변경하면 별도로 Update쿼리를 날릴 필요가 없다는 것이다. = Dirty Checking 이라고함.
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readOnly=true를하면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기에
                                    // 등록,수정,삭제 기능이 전혀 없는 서비스 메소드에서만 쓰는 것을 추천
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts)와 동일
                .collect(Collectors.toList());
        // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드이다.
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts); // JpaRepository에서 이미 delete메소드를 지원하고 있으니 이를 활용한다.
                                        // entity를 파라미터로 삭제할 수 있고, deleteById메소드를 이용햐면 id로 삭제할 수 있다.
                                        // 존재하는 Posts인지 확인을 위해 entity 조회 후 그대로 삭제한다.
    }
}
