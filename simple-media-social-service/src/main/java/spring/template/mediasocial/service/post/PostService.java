package spring.template.mediasocial.service.post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.constant.HeaderConstant;
import spring.template.mediasocial.dto.post.ReqCreatePost;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.UserEntity;
import spring.template.mediasocial.enums.PostEnum;
import spring.template.mediasocial.repository.PostRepository;
import spring.template.mediasocial.service.user.UserProviderService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    //repository
    private final PostRepository postRepository;
    //http servlet request
    private final HttpServletRequest request;
    // services
    private final UserProviderService userProviderService;
    private final PostHashtagService postHashtagService;
    private final PostUserTagService postUserTagService;

    @Transactional
     public void createPost(ReqCreatePost request){
        log.info("Create Post");
        //Create Post
         UserEntity loggedInUser = userProviderService.getLoggedInUser();
         PostEntity postEntity = new PostEntity();
         //set user
        postEntity.setUser(loggedInUser);
        //set content
        postEntity.setContent(request.getContent());
        postEntity.setContentType(request.getContentType());
        //set caption
        postEntity.setCaption(request.getCaption());
        //set location
        postEntity.setLocation(request.getLocation());
        //set hashtags
        postHashtagService.createPostHashtag(request.getPostHashtags(), postEntity);
        postUserTagService.createPostHashtag(request.getUserTags(), postEntity);
        //set status
        postEntity.setStatus(PostEnum.PUBLISHED);
        //set
        postRepository.save(postEntity);
         //Notify followers who are set to receive notifications
     }




}
