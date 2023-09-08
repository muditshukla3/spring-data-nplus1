package com.ms.service;

import com.ms.dto.Post;
import com.ms.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts(){
        List<com.ms.model.Post> posts = postRepository.findAll();
        List<Post> postDTOs = new ArrayList<>();
        posts.forEach(p -> {
            Post post = new Post(p.getSubject(),
                                getAllCommentsWithUserDetails(p),
                                p.getUser().getName());
            postDTOs.add(post);
        });
        return postDTOs;
    }

    private List<com.ms.dto.Comment> getAllCommentsWithUserDetails(com.ms.model.Post p) {
        List<com.ms.dto.Comment> comments = new ArrayList<>();
        p.getComments().forEach(comment -> comments.add(new com.ms.dto.Comment(comment.getReply(),comment.getUser().getName())));
        return comments;
    }

    public Post getById(Long id){
        Optional<com.ms.model.Post> postFromDb = postRepository.findById(id);
        if(postFromDb.isPresent()){
            var post = postFromDb.get();
            return new Post(post.getSubject(),
                    getAllCommentsWithUserDetails(post), post.getUser().getName());
        }
        return new Post("", null, "");
    }

    public List<Post> findAllByUserId(Long id){
        List<com.ms.model.Post> allByUserId = postRepository.getAllByUserId(id);
        List<Post> postDTOs = new ArrayList<>();
        allByUserId.forEach(p -> {
            Post post = new Post(p.getSubject(),
                    getAllCommentsWithUserDetails(p),
                    p.getUser().getName());
            postDTOs.add(post);
        });
        return postDTOs;
    }
}
