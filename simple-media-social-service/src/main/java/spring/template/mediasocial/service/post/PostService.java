package spring.template.mediasocial.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.post.ReqCreatePost;
import spring.template.mediasocial.repository.PostRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

     public void createPost(ReqCreatePost request){

}
