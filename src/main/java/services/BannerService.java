package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.BannerRepository;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	//managed repository---------------------
	@Autowired
	private BannerRepository bannerRepository;
	
	//supporting services -------------------
	
	//Basic CRUD methods --------------------
	public Banner create() {
		Banner created;
		created = new Banner();
		return created;
	}
	
	public Banner findOne(int bannerId) {
		Banner retrieved;
		retrieved = bannerRepository.findOne(bannerId);
		return retrieved;
	}
	
	public Banner save(Banner banner) {
		Banner saved;
		saved = bannerRepository.save(banner);
		return saved;
	}
	
	public void delete(Banner banner) {
		bannerRepository.delete(banner);
	}
	
	//Auxiliary methods ---------------------
	
	//Our other bussiness methods -----------
	
}
