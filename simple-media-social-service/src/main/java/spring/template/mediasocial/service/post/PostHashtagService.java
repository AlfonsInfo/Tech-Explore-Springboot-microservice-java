package spring.template.mediasocial.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.entity.HashTagEntity;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.PostHastagEntity;
import spring.template.mediasocial.repository.HashtagRepository;
import spring.template.mediasocial.repository.PostHashtagRepository;
import spring.template.mediasocial.service.hashtag.HashTagService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostHashtagService {
    //Repository
    private final HashtagRepository hashtagRepository;
    private final PostHashtagRepository postHashtagRepository;
    //Service
    private final HashTagService hashTagService;
    public void createPostHashtag(List<String> postHashtags, PostEntity postEntity) {
            hashTagService.create(postHashtags);
            List< HashTagEntity> hashTagEntityList = hashtagRepository.findAllByTagIn(postHashtags);
            hashTagEntityList.forEach(hashTagEntity -> {
                PostHastagEntity postHastagEntity = new PostHastagEntity();
                postHastagEntity.setPost(postEntity);
                postHastagEntity.setHashTagEntity(hashTagEntity);
                postHashtagRepository.save(postHastagEntity);
            });
    }

}
