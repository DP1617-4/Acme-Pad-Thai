package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Cook;
import domain.MasterClass;
import domain.VideoMaterial;

import repositories.VideoMaterialRepository;

@Service
@Transactional
public class VideoMaterialService {

	
	//Constructor
	public VideoMaterialService(){
		super();
	}
	
	//Managed Repository
	@Autowired
	private VideoMaterialRepository videoMaterialRepository;
	
	
	//Auxiliary Services
	@Autowired
	private CookService cookService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	//CRUD
	public VideoMaterial create(MasterClass masterClass){
		VideoMaterial result = new VideoMaterial();
		result.setMasterClass(masterClass);
		return result;
	}
	
	public VideoMaterial findOne(int id){
		VideoMaterial result;
		result = videoMaterialRepository.findOne(id);
		Assert.notNull(result);
		checkPrincipal(result);
		return result;
	}
	
	public Collection<VideoMaterial> findByMasterClass(int id){
		Collection<VideoMaterial> result;
		masterClassService.checkEnrolled(id);
		result = videoMaterialRepository.findAllByMasterClassId(id);
		Assert.notNull(result);
		return result;
	}
	
	public VideoMaterial save(VideoMaterial videoMaterial){
		VideoMaterial result;
		Assert.notNull(videoMaterial);
		checkPrincipal(videoMaterial);
		result = videoMaterialRepository.save(videoMaterial);
		return result;
	}
	
	public void delete(VideoMaterial videoMaterial){
		Assert.notNull(videoMaterial);
		checkPrincipal(videoMaterial);
		videoMaterialRepository.delete(videoMaterial);
	}
	//Business methods
	
	public void checkPrincipal(VideoMaterial videoMaterial){
		Cook cook;
		cook = cookService.findByPrincipal();
		Assert.isTrue(videoMaterial.getMasterClass().getCook().equals(cook), "Dear user, you must be the teacher of the master class to edit its materials.");
	}
}
