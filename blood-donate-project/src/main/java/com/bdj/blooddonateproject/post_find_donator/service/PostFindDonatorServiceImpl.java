package com.bdj.blooddonateproject.post_find_donator.service;

import java.util.Date;

import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.post_find_donator.dto.CreatePostDTO;
import com.bdj.blooddonateproject.post_find_donator.dto.PostFindDonatorDTO;
import com.bdj.blooddonateproject.post_find_donator.model.PostFindDonator;
import com.bdj.blooddonateproject.post_find_donator.repo.PostFindDonatorRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostFindDonatorServiceImpl implements PostFindDonatorService {

    @Autowired
    private PostFindDonatorRepo postFindDonatorRepo;

    public PostFindDonatorServiceImpl(PostFindDonatorRepo postFindDonatorRepo) {
        this.postFindDonatorRepo = postFindDonatorRepo;
    }

    @Override
    public Page<PostFindDonatorDTO> listPostFindDonator(Long id, Pageable pageable) {
        // TODO Auto-generated method stub
        return postFindDonatorRepo.listPostFindDonator(id, pageable).map(PostFindDonatorDTO::new);
    }

    @Override
    public PostFindDonator createNewPost(CreatePostDTO createPostDTO, Hospital hospital) {
        // TODO Auto-generated method stub

        PostFindDonator postFindDonator = new PostFindDonator();
        postFindDonator.setContent(createPostDTO.getContent());
        postFindDonator.setDeadlineRegister(createPostDTO.getDeadlineRegister());
        postFindDonator.setHospital(hospital);
        postFindDonator.setCreateAt((int) (new Date().getTime() / 1000));
        postFindDonator.setUpdateAt((int) (new Date().getTime() / 1000));
        postFindDonator.setIsDeleted(false);
        postFindDonatorRepo.save(postFindDonator);
        return postFindDonator;
    }

    @Override
    public PostFindDonatorDTO getPostFindDonator(Long id) {
        // TODO Auto-generated method stub
        PostFindDonator postFindDonator = postFindDonatorRepo.getPostFindDonator(id)
                .orElseThrow(() -> new IllegalStateException("post not found"));

        return new PostFindDonatorDTO(postFindDonator);
    }

    @Override
    public void updatePost(CreatePostDTO createPostDTO, Long id) {
        // TODO Auto-generated method stub
        PostFindDonator postFindDonator = this.findById(id);
        postFindDonator.setContent(createPostDTO.getContent());
        postFindDonator.setDeadlineRegister(createPostDTO.getDeadlineRegister());
        postFindDonator.setUpdateAt((int) (new Date().getTime() / 1000));
        postFindDonatorRepo.saveAndFlush(postFindDonator);

    }

    @Override
    public void deletePost(Long id) {
        // TODO Auto-generated method stub
        PostFindDonator postFindDonator = this.findById(id);
        postFindDonator.setIsDeleted(true);
        postFindDonatorRepo.saveAndFlush(postFindDonator);
    }

    @Override
    public PostFindDonator findById(Long id) {
        // TODO Auto-generated method stub
        return postFindDonatorRepo.findById(id).orElseThrow(() -> new IllegalStateException("not found post"));
    }

}
