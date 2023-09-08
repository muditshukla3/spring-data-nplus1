package com.ms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name="POSTS")
@NoArgsConstructor
@Getter
@NamedEntityGraph(name = "post-entity-graph",
                  attributeNodes = {
                      @NamedAttributeNode("subject"),
                      @NamedAttributeNode("comments"),
                      @NamedAttributeNode("user")
})
@NamedEntityGraph(
        name = "post-entity-graph-with-user-comments",
        attributeNodes = {
                @NamedAttributeNode("subject"),
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "comments", subgraph = "comments-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "comments-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("user")
                        }
                )
        }
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Comment> comments=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public Post(String subject, User user) {
        this.subject = subject;
        this.user = user;
    }
}
