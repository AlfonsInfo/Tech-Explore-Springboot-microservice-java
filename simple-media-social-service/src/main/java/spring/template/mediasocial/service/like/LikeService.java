package spring.template.mediasocial.service.like;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.like.ReqCreateLikeDto;
import spring.template.mediasocial.entity.LikeEntity;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.UserEntity;
import spring.template.mediasocial.repository.LikeRepository;
import spring.template.mediasocial.service.user.UserProviderService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

        private final LikeRepository likeRepository;
        private final UserProviderService userProviderService;
        public void createLike(ReqCreateLikeDto request){
            UserEntity loggedInUser = userProviderService.getLoggedInUser();
            PostEntity postEntity = PostEntity.builder().id(request.getPostId()).build();
            //Validate if user has already liked the post
            if(likeRepository.existsByUser_IdAndPost_Id(loggedInUser.getId(), postEntity.getId())){
                log.info("User has already liked the post");
                return;
            }
            //Create Like
            LikeEntity likeEntity = new LikeEntity();
            likeEntity.setUser(loggedInUser);
            likeEntity.setPost(postEntity);
            likeRepository.save(likeEntity);
        }
}
