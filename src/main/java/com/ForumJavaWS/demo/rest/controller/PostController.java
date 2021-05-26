package com.ForumJavaWS.demo.rest.controller;

import java.util.Date;

import com.ForumJavaWS.demo.rest.entity.EnumReportReason;
import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Report;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.payload.response.MessageResponse;
import com.ForumJavaWS.demo.rest.payload.response.PostResponse;
import com.ForumJavaWS.demo.rest.repository.PostRepository;
import com.ForumJavaWS.demo.rest.repository.ReportRepository;
import com.ForumJavaWS.demo.rest.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReportRepository reportRepository;

    // Fonction permettant de récupérer un post
    @ResponseBody
    @GetMapping("/post/{postId}")
    public Post getPostById(final @PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId);
        return post;
    }

    // Fonction permettant de supprimer un post
    @DeleteMapping("/post/{postId}")
    public void deletePostById(final @PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId);
        Topic containerTopic = post.getTopic();
        if (post.getId() != containerTopic.getPosts().get(0).getId()) {
            containerTopic.getPosts().remove(post);
            postRepository.delete(post);
        }
    }

    // Fonction permettant de modifier un post, il ajoute un "update at"
    @PutMapping("/post/{id}")
    public ResponseEntity<?> editPost(@PathVariable("id") Long id, @RequestBody PostResponse postResponse) {
        Post post = this.postRepository.findById(id);
        Long creator = post.getUser().getId();
        if (creator == UserDetailsServiceImpl.getCurrentUser().getId()) {
            Date date = new Date();
            post.setUpdatedAt(date);
            post.setContent(postResponse.getContent());
            return ResponseEntity.ok(this.postRepository.save(post));
        } else {
            return ResponseEntity.ok(new MessageResponse(ApiMessage.ERROR_NOT_CREATOR, "Vous ne pouvez pas modifier ce post car vous n'êtes pas le créateur."));
        }
    }

    // Fonction permettant de signaler un post
    @GetMapping("/post/{postId}/reports")
    public Page<Report> getReportsByPost(final @PathVariable("postId") Long postId, Pageable pageable) {
        Post post = postRepository.findById(postId);
        return reportRepository.findByPostOrderById(post, pageable);
    }

    // Fonction permettant de faire un signalement
    @PostMapping("/post/{postId}/report")
    public Report addreport(final @PathVariable("postId") Long postId, @RequestBody Report report) throws Exception {
        Post post = postRepository.findById(postId);
        if(report.getReason().toString() == "SPAM"){
            report.setReason(EnumReportReason.SPAM);
        } else if (report.getReason().toString() == "NUDITY"){
            report.setReason(EnumReportReason.NUDITY);
        } else if (report.getReason().toString() == "RACISM"){
            report.setReason(EnumReportReason.RACISM);
        } else{
            report.setReason(EnumReportReason.OTHER);
        }

        report.setUser(UserDetailsServiceImpl.getCurrentUser());
        post.getReports().add(report);
        report.setPost(post);
        postRepository.save(post);
        return report;
    }
}
