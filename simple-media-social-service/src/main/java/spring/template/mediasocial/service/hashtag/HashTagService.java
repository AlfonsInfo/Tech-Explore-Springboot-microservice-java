package spring.template.mediasocial.service.hashtag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.hashtag.ReqCreateHashTag;
import spring.template.mediasocial.entity.HashTagEntity;
import spring.template.mediasocial.repository.HashtagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HashTagService {

    private final HashtagRepository hashtagRepository;

    public void create(List<String> tags) {
        log.info("Create HashTag");
        List<String> newTags = tags.stream().filter(tag -> !hashtagRepository.existsByTag(tag)).toList();
        List<HashTagEntity> hashtags = new ArrayList<>();
        newTags.forEach(tag -> hashtags.add(HashTagEntity.builder().tag(tag).build()));
        hashtagRepository.saveAll(hashtags);
        int countNewTags = newTags.size();
        log.info("Created {} new tags", countNewTags);
    }
}
