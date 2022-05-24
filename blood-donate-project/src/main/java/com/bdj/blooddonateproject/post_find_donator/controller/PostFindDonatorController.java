package com.bdj.blooddonateproject.post_find_donator.controller;

import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.hospital.service.HospitalService;
import com.bdj.blooddonateproject.post_find_donator.dto.CreatePostDTO;
import com.bdj.blooddonateproject.post_find_donator.dto.PostFindDonatorDTO;
import com.bdj.blooddonateproject.post_find_donator.service.PostFindDonatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostFindDonatorController {

    @Autowired
    private PostFindDonatorService postFindDonatorService;

    @Autowired
    private HospitalService hospitalService;

    public PostFindDonatorController(PostFindDonatorService postFindDonatorService, HospitalService hospitalService) {
        this.postFindDonatorService = postFindDonatorService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/my-post")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> getListPostFindDonator(Pageable pageable) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;

            Hospital hospital = hospitalService.findInfoHospital(userPrincipal.getUsername());
            Page<PostFindDonatorDTO> listPost = postFindDonatorService.listPostFindDonator(hospital.getId(), pageable);
            return new ResponseEntity<Page<PostFindDonatorDTO>>(listPost, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");

    }

    @GetMapping("/detail/{key}")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> getDetailPost(@PathVariable("key") Long key) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            PostFindDonatorDTO postFindDonatorDTO;
            try {
                postFindDonatorDTO = postFindDonatorService.getPostFindDonator(key);
            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.badRequest().body("error: " + e.getMessage());
            }
            return new ResponseEntity<PostFindDonatorDTO>(postFindDonatorDTO, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> createNewPost(@RequestBody CreatePostDTO newPost) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            Hospital hospital = hospitalService.findInfoHospital(userPrincipal.getUsername());
            try {
                postFindDonatorService.createNewPost(newPost, hospital);

            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.badRequest().body("error: " + e.getMessage());
            }
            return ResponseEntity.ok().body("message: " + "CREATE SUCCESS");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");

    }

    @PutMapping("/{key}")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> updatePost(@RequestBody CreatePostDTO updatePost, @PathVariable("key") Long key) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            System.out.println(updatePost.getContent());
            try {
                postFindDonatorService.updatePost(updatePost, key);
            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.badRequest().body("error: " + e.getMessage());
            }
            return ResponseEntity.ok().body("message: " + "UPDATE SUCESS");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            try {
                postFindDonatorService.deletePost(id);
            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.badRequest().body("error: " + e.getMessage());
            }
            return ResponseEntity.ok().body("message: " + "DELETE SUCCESS");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

}
