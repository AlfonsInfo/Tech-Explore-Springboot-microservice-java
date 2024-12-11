package spring.template.mediasocial.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.user.ResTotalPosts;
import spring.template.mediasocial.repository.PostRepository;
import spring.template.mediasocial.repository.UserRepository;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    //Repository
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public ResTotalPosts getUserTotalPosts(String userId) {
        int totalPosts = postRepository.countByUser_Id(Long.parseLong(userId));
        ResTotalPosts resTotalPosts = new ResTotalPosts();
        resTotalPosts.setTotalPosts(totalPosts);
        return resTotalPosts;
    }





}
