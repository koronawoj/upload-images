package pl.koronawoj.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pl.koronawoj.model.Photo;
import pl.koronawoj.model.User;
import pl.koronawoj.service.PhotoService;

@RestController
@RequestMapping("/rest")
public class PhotoResource {

	private String imageName;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private PhotoResource pr;

	Photo[] photoList = new Photo[3];

	Photo currentPhoto;


	@EventListener(ContextRefreshedEvent.class)
	public void bla() {
		if(photoService.findByPhotoId(1L) == null && photoService.findByPhotoId(2L) == null && photoService.findByPhotoId(3L) == null){
		
		Photo photo1 = new Photo("Name-1", "Title-1", "Description-1", "1.jpg", null, null, 2, null);
		Photo photo2 = new Photo("Name-2", "Title-2", "Description-2", "2.jpg", null, null, 1, null);
		Photo photo3 = new Photo("Name-3", "Title-3", "Description-3", "3.jpg", null, null, 3, null);

		photoList[0] = photo1;
		photoList[1] = photo2;
		photoList[2] = photo3;
		
		for(int i = 0; i < 3; i++){
			dodaj(photoList[i]);
		}
		}
	}

	public Photo dodaj(Photo photo) {
		return photoService.save(photo);
	}

	@RequestMapping(value = "/photo/upload", method = RequestMethod.POST)
	public String upload(HttpServletResponse response, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		Iterator<String> it = multipartRequest.getFileNames();
		MultipartFile multipartFile = multipartRequest.getFile(it.next());
		String fileName = multipartFile.getOriginalFilename();
		imageName = fileName;

		String path = new File("src/main/resources/static/images").getAbsolutePath() + "\\" + fileName;
		try {
			multipartFile.transferTo(new File(path));
			System.out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Uploaded Success!";
	}

	@RequestMapping(value = "/photo/add", method = RequestMethod.POST)
	public Photo addPhoto(@RequestBody Photo photo) {
		photo.setImageName(imageName);
		return photoService.save(photo);
	}

	@RequestMapping(value = "/photo/user", method = RequestMethod.POST)
	public List<Photo> getPhotosByUser(@RequestBody User user) {
		return photoService.findByUser(user);
	}

	@RequestMapping(value = "/photo/photoId", method = RequestMethod.POST)
	public Photo getPhotoByPhotoId(@RequestBody Long photoId) {
		return photoService.findByPhotoId(photoId);
	}

	@RequestMapping(value = "/photo/update", method = RequestMethod.POST)
	public void updatePhoto(@RequestBody Photo photo) {
		Photo currentPhoto = photoService.findByPhotoId(photo.getPhotoId());
		currentPhoto.setLikes(photo.getLikes());
		photoService.save(currentPhoto);
	}

}
