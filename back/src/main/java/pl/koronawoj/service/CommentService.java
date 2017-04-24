package pl.koronawoj.service;

import java.util.List;

import pl.koronawoj.model.Comment;

public interface CommentService {
	
	Comment save(Comment comment);
	
	List<Comment> findByPhotoId (Long photoId);
	
	Comment findOne(Long commentId);
	

}
