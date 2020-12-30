package com.jbook.post;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jbook.user.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This is our model for social media post")
@Entity
public class Post {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer id;
	
	@Size(min = 2, message = "Post should have atleast 2 characters")
	@ApiModelProperty(notes = "Post should have atleast 2 characters")
	private String description;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	

}
