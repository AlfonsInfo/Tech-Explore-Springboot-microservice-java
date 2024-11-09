package spring.template.mediasocial.service.hashtag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.like.ProjectionLikeCount;
import spring.template.mediasocial.dto.post.ResPostDto;
import spring.template.mediasocial.entity.HashTagEntity;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.PostHashtagEntity;
import spring.template.mediasocial.enums.PostEnum;
import spring.template.mediasocial.repository.HashtagRepository;
import spring.template.mediasocial.repository.LikeRepository;
import spring.template.mediasocial.repository.PostHashtagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class HashTagService {

    private final HashtagRepository hashtagRepository;
    private final PostHashtagRepository postHashtagRepository;
    private final LikeRepository likeRepository;

    public void create(List<String> tags) {
        log.info("Create HashTag");
        List<String> newTags = tags.stream().filter(tag -> !hashtagRepository.existsByTag(tag)).toList();
        List<HashTagEntity> hashtags = new ArrayList<>();
        newTags.forEach(tag -> hashtags.add(HashTagEntity.builder().tag(tag).build()));
        hashtagRepository.saveAll(hashtags);
        int countNewTags = newTags.size();
        log.info("Created {} new tags", countNewTags);
    }

    public List<String> getTags() {
        log.info("Get HashTags");
        return hashtagRepository.findAll().stream().map(HashTagEntity::getTag).toList();
    }

    public List<ResPostDto> getPostsByTag(String tag) {
        log.info("Get Posts by Tag");
        List<PostHashtagEntity> listPostHashtag = postHashtagRepository.findAllByHashTagEntity_IdAndPost_Status(
                UUID.fromString(tag),
                PostEnum.PUBLISHED
        );
        List<PostEntity> listPost = new ArrayList<>();
        listPostHashtag.forEach(postHashtag -> listPost.add(postHashtag.getPost()));
        List<ProjectionLikeCount> mapPostCount = likeRepository.countByPost_IdInGroupByPost_id(listPost.stream().map(PostEntity::getId).toList());
        return listPostHashtag.stream().map(postHashtag -> {
            ResPostDto resPostDto = new ResPostDto();
            resPostDto.setId(postHashtag.getPost().getId());
            resPostDto.setUserId(postHashtag.getPost().getUser().getId().toString());
            resPostDto.setUserName(postHashtag.getPost().getUser().getUsername());
            resPostDto.setContent(postHashtag.getPost().getContent());
            resPostDto.setCaption(postHashtag.getPost().getCaption());
            resPostDto.setLocation(postHashtag.getPost().getLocation());
            resPostDto.setTotalLike(
                    mapPostCount
                            .stream()
                            .filter(count -> count.getPostId().equals(postHashtag.getPost().getId()))
                            .findFirst()
                            .orElse(new ProjectionLikeCount(UUID.randomUUID(),0))
                            .getCount().toString()
            );
            return resPostDto;
        }).toList();
    }

}
