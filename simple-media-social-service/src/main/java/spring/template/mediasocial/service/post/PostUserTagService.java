package spring.template.mediasocial.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.entity.HashTagEntity;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.PostHastagEntity;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostUserTagService {

    public void createPostHashtag(List<String> postHashtags, PostEntity postEntity) {
//        List<HashTagEntity> hashTagEntityList = hashtagRepository.findAllByTagIn(postHashtags);
//        hashTagEntityList.forEach(hashTagEntity -> {
//            PostHastagEntity postHastagEntity = new PostHastagEntity();
//            postHastagEntity.setPost(postEntity);
//            postHastagEntity.setHashTagEntity(hashTagEntity);
//            postHashtagRepository.save(postHastagEntity);
//        });
    }

}
