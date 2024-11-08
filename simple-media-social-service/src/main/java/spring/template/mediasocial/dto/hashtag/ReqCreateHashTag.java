package spring.template.mediasocial.dto.hashtag;

import lombok.Data;

import java.util.List;

@Data
public class ReqCreateHashTag {
    private List<String> tags;
}
