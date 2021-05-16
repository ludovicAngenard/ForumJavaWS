package com.ForumJavaWS.demo.rest.controller;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.repository.CategoryRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;
import com.ForumJavaWS.demo.rest.DTO.TopicDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;
    private CategoryRepository categoryRepository;

    @ResponseBody
    @GetMapping("/categories/{categoryId}/topics")
    public List<Topic> findTopicsByCategory(final @PathVariable("categoryId") Integer categoryid){
        try{
            Category category = categoryRepository.findById(categoryid).get();
            List<Topic> topics = topicRepository.findByCategoryOrderByTitle(category);
            return topics;
        } catch (Exception e){
            return null;
        }

    }

    @ResponseBody
    @GetMapping("/categories/{categoryId}/topics/{id}")
    public Topic findTopicById(final @PathVariable("id") Integer topicId){
        try{
            Topic topic = topicRepository.findById(topicId).get();
            return topic;
        } catch (Exception e){
            return null;
        }

    }

    @PostMapping("/topic")
    public Topic addTopic(@RequestBody Topic topic){
        // TODO
        // CHECK IF HE HAS A TITLE
        // CREATE A POST
        return topicRepository.save(topic);
    }

    // TODO
    // ONLY HIS CREATOR CAN DELETE THE TOPIC IF THERE IS ONLY ONE POST
    @DeleteMapping("/topics/{id}")
    public void deleteTopic(final @PathVariable("id") Integer topicId){
        topicRepository.deleteById(topicId);
    }

    // TODO
    // EDITED ONLY BY A MODERATOR OR AN ADMIN
    @PutMapping("/topics/{id}")
    public Topic editTopic(@PathVariable("id") Integer id, @RequestBody TopicDTO topicDTO ){
        Topic topic = this.topicRepository.getOne(id);
        topic.setLocked(topicDTO.getLocked());
        return this.topicRepository.save(topic);
    }
}
