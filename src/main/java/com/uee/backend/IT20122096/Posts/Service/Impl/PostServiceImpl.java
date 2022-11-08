package com.uee.backend.IT20122096.Posts.Service.Impl;

import com.uee.backend.IT20122096.Campaigns.DTO.ContributionResponseDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import com.uee.backend.IT20122096.Campaigns.Service.Abstract.CampaignService;
import com.uee.backend.IT20122096.Campaigns.Service.Abstract.ContributionService;
import com.uee.backend.IT20122096.Posts.DTO.CommentResponseDTO;
import com.uee.backend.IT20122096.Posts.DTO.PostDTO;
import com.uee.backend.IT20122096.Posts.DTO.PostResponseDTO;
import com.uee.backend.IT20122096.Posts.Entity.Post;
import com.uee.backend.IT20122096.Posts.Repository.PostRepository;
import com.uee.backend.IT20122096.Posts.Service.CommentService;
import com.uee.backend.IT20122096.Posts.Service.PostService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;
    final
    CampaignService campaignService;
    final
    CommentService commentService;
    final
    ContributionService contributionService;

    public PostServiceImpl(PostRepository postRepository, CampaignService campaignService, CommentService commentService, ContributionService contributionService) {
        this.postRepository = postRepository;
        this.campaignService = campaignService;
        this.commentService = commentService;
        this.contributionService = contributionService;
    }

    @Override
    public Post savePost(PostDTO postDTO) {

        Post post = new Post();
        post.setUserId(postDTO.getUserId());
        post.setCampaignId(postDTO.getCampaignId());
        post.setDescription(postDTO.getDescription());
        post.setImage(postDTO.getImage());

        try {
            return postRepository.save(post);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        try {
            List<Post> postList=postRepository.findAll();
            List<PostResponseDTO> finalPostList = new ArrayList<>();

            for (Post post: postList) {

                PostResponseDTO postResponse = new PostResponseDTO();
                postResponse.setId(post.getId());
                postResponse.setUserId(post.getUserId());

                Optional<Campaign> campaign = campaignService.getCampaignById(post.getCampaignId());
                if(campaign.isPresent()){
                    postResponse.setCampaign(campaign.get());
                }else {
                    postResponse.setCampaign(null);
                }

                postResponse.setDescription(post.getDescription());
                postResponse.setImage(post.getImage());
                postResponse.setLikeCount(5);

                List<CommentResponseDTO> comments = commentService.getAllComments(post.getId());
                postResponse.setCommentsCount(comments.size());
                postResponse.setComments(comments);

                List<ContributionResponseDTO> contributors = contributionService.getAllContribution(post.getCampaignId());
                postResponse.setContributors(contributors);

                finalPostList.add(postResponse);
            }

            return finalPostList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<PostResponseDTO> getAllPostsByUserId(ObjectId userId) {

        try {
            List<Post> postList=postRepository.getAllPostsByUserId(userId);
            List<PostResponseDTO> finalPostList = new ArrayList<>();

            for (Post post: postList) {

                PostResponseDTO postResponse = new PostResponseDTO();
                postResponse.setId(post.getId());
                postResponse.setUserId(post.getUserId());

                Optional<Campaign> campaign = campaignService.getCampaignById(post.getCampaignId());
                if(campaign.isPresent()){
                    postResponse.setCampaign(campaign.get());
                }else {
                    postResponse.setCampaign(null);
                }

                postResponse.setDescription(post.getDescription());
                postResponse.setImage(post.getImage());
                postResponse.setLikeCount(5);

                List<CommentResponseDTO> comments = commentService.getAllComments(post.getId());
                postResponse.setCommentsCount(comments.size());
                postResponse.setComments(comments);

                List<ContributionResponseDTO> contributors = contributionService.getAllContribution(post.getCampaignId());
                postResponse.setContributors(contributors);

                finalPostList.add(postResponse);
            }

            return finalPostList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public PostResponseDTO getPostsById(ObjectId postId) {
        try {
            Optional<Post> postOptional=postRepository.findById(postId);
            if(postOptional.isEmpty()){
                throw new NullPointerException();
            }
            Post post=postOptional.get();

            PostResponseDTO postResponse = new PostResponseDTO();
            postResponse.setId(post.getId());
            postResponse.setUserId(post.getUserId());

            Optional<Campaign> campaign = campaignService.getCampaignById(post.getCampaignId());
            if(campaign.isPresent()){
                postResponse.setCampaign(campaign.get());
            }else {
                postResponse.setCampaign(null);
            }

            postResponse.setDescription(post.getDescription());
            postResponse.setImage(post.getImage());
            postResponse.setLikeCount(5);

            List<CommentResponseDTO> comments = commentService.getAllComments(post.getId());
            postResponse.setCommentsCount(comments.size());
            postResponse.setComments(comments);

            List<ContributionResponseDTO> contributors = contributionService.getAllContribution(post.getCampaignId());
            postResponse.setContributors(contributors);
            return postResponse;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean deletePost(ObjectId postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
