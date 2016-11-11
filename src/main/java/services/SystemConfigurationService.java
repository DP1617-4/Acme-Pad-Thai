package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SystemConfigurationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Banner;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	//managed repository---------------------
	@Autowired
	private SystemConfigurationRepository systemConfigurationRepository;
	
	//supporting services -------------------
	
	//Basic CRUD methods --------------------
	public SystemConfiguration create() {
		SystemConfiguration created;
		created = new SystemConfiguration();
		return created;
	}
	
	public SystemConfiguration findOne(int systemConfigurationId) {
		SystemConfiguration retrieved;
		retrieved = systemConfigurationRepository.findOne(systemConfigurationId);
		return retrieved;
	}
	
	public SystemConfiguration save(SystemConfiguration systemConfiguration) {
		SystemConfiguration saved;
		saved = systemConfigurationRepository.save(systemConfiguration);
		return saved;
	}
	
	public void delete(SystemConfiguration systemConfiguration) {
		systemConfigurationRepository.delete(systemConfiguration);
	}
	
	//Auxiliary methods ---------------------
	
	//Our other bussiness methods -----------
	public SystemConfiguration save2(SystemConfiguration systemConfiguration) { // Requirement 34.1
		UserAccount admin;
		admin = LoginService.getPrincipal();
		Assert.isTrue(systemConfiguration.gadasdaas.equals(admin)); // No se como hacer que sea un admin.
		SystemConfiguration saved = this.save(systemConfiguration);
		return saved;
	}
}
