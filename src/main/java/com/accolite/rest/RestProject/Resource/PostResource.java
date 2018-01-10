package com.accolite.rest.RestProject.Resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.accolite.rest.RestProject.Model.Post;
import com.accolite.rest.RestProject.Service.PostService;

@Path("users/{userID}/posts")
public class PostResource {
PostService service = new PostService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Post> getAllPosts(@PathParam("userID") int userID) {
		return service.getPosts(userID);
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Post getPostByID(@PathParam("id") int id, @PathParam("userID") int userID) {
		return service.getPostByID(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPost(Post post, @PathParam("userID") int userID) {
		service.addPost(post, userID);
	}
	
	@Path("/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Post updatePost(@PathParam("id") int id, Post post, @PathParam("userID") int userID) {
		return service.updatePost(id, post);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post deletePost(@PathParam("id") int id, @PathParam("userID") int userID) {
		return service.deletePost(id);
	}
}

