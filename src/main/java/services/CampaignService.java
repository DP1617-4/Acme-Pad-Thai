package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CampaignRepository;
import security.LoginService;
import security.UserAccount;
import domain.Campaign;
import domain.Sponsor;

@Service
@Transactional
public class CampaignService {

	//managed repository---------------------
	@Autowired
	private CampaignRepository campaignRepository;
	
	//supporting services -------------------
	@Autowired
	private SponsorService sponsorService;
	
	//Basic CRUD methods --------------------
	public Campaign create() { 
		Sponsor sponsor = sponsorService.findOneByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		Campaign created;
		created = new Campaign();
//		Date moment = new Date(System.currentTimeMillis()-100);
//		Assert.isTrue(moment.before(created.getStartDate()));
//		Assert.isTrue(created.getStartDate().before(created.getEndDate()));
		created.setDeleted(false);
		created.setStarred(false);
		created.setSponsor(sponsor);
		return created;
	}
	
	public Campaign findOne(int campaignId) {
		Campaign retrieved;
		retrieved = campaignRepository.findOne(campaignId);
		return retrieved;
	}
	
	public Collection<Campaign> findAllNotDeleted() {
		Collection<Campaign> notDeleted = new ArrayList<Campaign>();
		for(Campaign c: campaignRepository.findAll()){
			if(c.getDeleted()==false){
				notDeleted.add(c);
			}
		}
		return notDeleted;
	}
	
	public Campaign save(Campaign campaign) { // Fechas en futuro lo haremos también en Javascript.
		Campaign saved;
		Date moment = new Date(System.currentTimeMillis()-100);
		Assert.isTrue(moment.before(campaign.getStartDate()));
		Assert.isTrue(campaign.getStartDate().before(campaign.getEndDate()));
		saved = campaignRepository.save(campaign);
		return saved;
	}
	
	public void delete(Campaign campaign) {
		campaignRepository.delete(campaign);
	}
	
	//Auxiliary methods ---------------------
	private boolean activeCampaign(Campaign c) {
		boolean res = false;
		Date moment = new Date();
		if(moment.after(c.getStartDate()) && moment.before(c.getEndDate())) {
			res = true;
		}
		return res;
	}
	
	private boolean campaignPassed(Campaign c) {
		boolean res = false;
		Date moment = new Date();
		if(moment.after(c.getStartDate())) {
			res = true;
		}
		return res;
	}
	
	//Our other bussiness methods -----------
	public Campaign save2(Campaign campaign) { // Requirement 33.1
		UserAccount sponsor;
		sponsor = LoginService.getPrincipal();
		Assert.isTrue(campaign.getSponsor().equals(sponsor));
		Assert.isTrue(!campaignPassed(campaign));
		Assert.isTrue(!activeCampaign(campaign));
		Campaign saved = this.save(campaign);
		return saved;
	}
	
	public Campaign delete2(Campaign campaign) { // Requirement 33.1
		UserAccount sponsor;
		sponsor = LoginService.getPrincipal();
		Assert.isTrue(campaign.getSponsor().equals(sponsor));
		Assert.isTrue(!campaignPassed(campaign));
		Assert.isTrue(!activeCampaign(campaign));
		campaign.setDeleted(true);
		Campaign saved = this.save(campaign); // Al borrar, no deberia borrarse del repo?
		return saved;
	}
	
//	public Campaign restore(Campaign campaign){ TIENE QUE ESTAR ESTO??
//		campaign.setDeleted(false);
//		Campaign saved = this.save(campaign);
//		return saved;
//	}
}
