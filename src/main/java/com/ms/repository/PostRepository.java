package com.ms.repository;

import com.ms.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(value = "post-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    //@EntityGraph(value = "post-entity-graph-with-user-comments", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> findById(Long id);

    // ad-hoc entity graph
    @EntityGraph(attributePaths = { "subject","user" })
    List<Post> getAllByUserId(Long id);

}
