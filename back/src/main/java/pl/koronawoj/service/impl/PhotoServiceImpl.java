package pl.koronawoj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.koronawoj.dao.PhotoDao;
import pl.koronawoj.model.Photo;
import pl.koronawoj.model.User;
import pl.koronawoj.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDao photoDao;

	@Override
	public Photo save(Photo photo) {
		return photoDao.save(photo);
	}

	@Override
	public List<Photo> findAll() {
		return photoDao.findAll();
	}

	@Override
	public List<Photo> findByUser(User user) {
		return photoDao.findByUser(user);
	}

	@Override
	public Photo findByPhotoId(Long photoId) {
		return photoDao.findByPhotoId(photoId);
	}

}
