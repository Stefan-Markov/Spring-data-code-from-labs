package spring.jsonlab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.jsonlab.dao.PostRepository;
import spring.jsonlab.entities.Post;
import spring.jsonlab.exception.NonexistingEntityException;
import spring.jsonlab.service.PostService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;


    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return this.postRepository.findById(id).orElseThrow(() ->
                new NonexistingEntityException(String.format("Post with ID=%s", id)));

    }

    @Override
    @Transactional
    public Post addPost(Post post) {
        post.setId(null);
        return postRepository.save(post);

    }

    @Override
    @Transactional
    public Post updatePost(Post post) {
        getPostById(post.getId());
        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post deletePost(Long id) {
        Post removed = getPostById(id);
        postRepository.deleteById(id);
        return removed;
    }

    @Override
    public long getPostsCount() {
        return this.postRepository.count();
    }
}
