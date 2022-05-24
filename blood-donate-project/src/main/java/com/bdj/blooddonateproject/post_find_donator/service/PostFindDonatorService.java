package com.bdj.blooddonateproject.post_find_donator.service;

import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.post_find_donator.dto.CreatePostDTO;
import com.bdj.blooddonateproject.post_find_donator.dto.PostFindDonatorDTO;
import com.bdj.blooddonateproject.post_find_donator.model.PostFindDonator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostFindDonatorService {

    Page<PostFindDonatorDTO> listPostFindDonator(Long id, Pageable pageable);

    void createNewPost(CreatePostDTO createPostDTO, Hospital hospital);

    PostFindDonatorDTO getPostFindDonator(Long id);

    void updatePost(CreatePostDTO createPostDTO, Long id);

    void deletePost(Long id);

    PostFindDonator findById(Long id);

}
