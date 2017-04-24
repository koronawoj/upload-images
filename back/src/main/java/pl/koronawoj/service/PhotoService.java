package pl.koronawoj.service;

import java.util.List;

import pl.koronawoj.model.Photo;
import pl.koronawoj.model.User;

public interface PhotoService {

	Photo save(Photo photo);

	List<Photo> findAll();

	List<Photo> findByUser(User user);

	Photo findByPhotoId(Long photoId);

}
