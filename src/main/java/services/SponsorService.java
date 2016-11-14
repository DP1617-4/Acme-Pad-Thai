package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Campaign;
import domain.Cook;
import domain.Folder;
import domain.MasterClass;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	// managed repository---------------------
	@Autowired
	private SponsorRepository sponsorRepository;

	// supporting services -------------------

	// Basic CRUD methods --------------------
	public Sponsor create() {
		Sponsor sponsor = new Sponsor();
		Collection<Folder> folders = new ArrayList<Folder>();
		//TODO crear folders para sponsor
		/*
		Folder inbox = folderService.create(sponsor);
		inbox.setName("Inbox");
		inbox.setSystemFolder(true);
		inbox.setDeleted(false);
		folders.add(inbox);
		
		Folder outbox = folderService.create(sponsor);
		outbox.setName("OutBox");
		outbox.setSystemFolder(true);
		outbox.setDeleted(false);
		cook.setFolders(folders);
		folders.add(outbox);
		Folder trash = folderService.create(sponsor);
		trash.setName("Trash");
		trash.setSystemFolder(true);
		trash.setDeleted(false);
		folders.add(trash);
		Folder spam = folderService.create(sponsor);
		spam.setName("Spam");
		spam.setSystemFolder(true);
		spam.setDeleted(false);
		folders.add(spam);
		*/
		sponsor.setFolders(folders);
		sponsor.setCampaigns(new ArrayList<Campaign>());
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		authority.setAuthority(Authority.SPONSOR);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		
		sponsor.setUserAccount(userAccount);
		return sponsor;
	}

	public Sponsor findOne(int sponsorId) {
		Sponsor retrieved;
		retrieved = sponsorRepository.findOne(sponsorId);
		return retrieved;
	}

	public Sponsor save(Sponsor sponsor) {
		Sponsor saved;
		saved = sponsorRepository.save(sponsor);
		return saved;
	}

	public void delete(Sponsor sponsor) {
		sponsorRepository.delete(sponsor);
	}

	// Auxiliary methods ---------------------

	// Our other bussiness methods -----------
	public Collection<Double> calculateMinAvgMaxFromCampaignsOfSponsors() {
		return sponsorRepository.calculateMinAvgMaxFromCampaignsOfSponsors();
	}

	public Collection<Double> calculateMinAvgMaxFromCampaignsOfSponsorsByDate() {
		return sponsorRepository
				.calculateMinAvgMaxFromCampaignsOfSponsorsByDate();
	}

	public Collection<String> findCompaniesNameOfSponsors() {
		return sponsorRepository.findCompaniesNameOfSponsors();
	}

	public Collection<String> findCompaniesNameOfSponsorsByBills() {
		return sponsorRepository.findCompaniesNameOfSponsorsByBills();
	}

	public Sponsor findInnactiveSponsorInThreeMonths() {
		return sponsorRepository.findInnactiveSponsorInThreeMonths();
	}

	public Collection<String> findCompaniesNameWhichSpentLessInCampaignsThanAvg() {
		return sponsorRepository
				.findCompaniesNameWhichSpentLessInCampaignsThanAvg();
	}

	public Collection<String> findCompaniesNameSpent90Percent() {
		return sponsorRepository.findCompaniesNameSpent90Percent();
	}
	
	public Sponsor findOneByPrincipal(){
		Sponsor sponsor = sponsorRepository.findOneByUserAccountId(LoginService.getPrincipal().getId());
		return sponsor;
	}
}
