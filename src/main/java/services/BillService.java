package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BillRepository;
import domain.Bill;
import domain.Campaign;
import domain.Sponsor;
import domain.SystemConfiguration;

@Service
@Transactional
public class BillService {

	//managed repository---------------------
	@Autowired
	private BillRepository billRepository;
	
	//supporting services -------------------
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private SystemConfigurationService systemConfigurationService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private BannerService bannerService;
	
	//Basic CRUD methods --------------------
	public Bill create() {
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		Bill created;
		created = new Bill();
		Date moment = new Date(System.currentTimeMillis()-100);
		created.setCreationDate(moment);
		return created;
	}
	
	public Bill findOne(int billId) {
		Bill retrieved;
		retrieved = billRepository.findOne(billId);
		return retrieved;
	}
	
	public Collection<Bill> findAll(){
		Collection<Bill> bills;
		bills = billRepository.findAll();
		return bills;
	}
	
	public Bill save(Bill bill) {
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		Bill saved;
		saved = billRepository.save(bill);
		return saved;
	}
	
	public void delete(Bill bill) {
		billRepository.delete(bill);
	}
	
	//Auxiliary methods ---------------------
	
	//Our other bussiness methods -----------
	public Double[][][] calculateAvgDevPaidAndUnpaidBills() {
		return billRepository.calculateAvgDevPaidAndUnpaidBills();
	}
	
	public Bill save2(Bill bill) { // Requirement 33.3
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		Date moment = new Date(System.currentTimeMillis()-100);
		bill.setPaymentDate(moment);
		Bill saved = this.save(bill);
		return saved;
	}
	
	public void computeProcedureMonthlyBills() { // Requirement 34.2
		administratorService.checkAdministrator();
		Collection<SystemConfiguration> configs = systemConfigurationService.findAll();
		SystemConfiguration sys = configs.iterator().next();
		Double fee = sys.getFee();
		Date moment = new Date();
		for (Sponsor s : sponsorService.findAll()){
			for (Campaign c : s.getCampaigns()) {
			}
		}
	}
	
//	public void sendMessageSponsors() { // Requirement 34.3
//		Administrator admin = administratorService.findSystem();
//		Assert.notNull(admin,"Dear user, you are not the admin.");
//		for (Sponsor s : sponsorService.findAll()){
//			for (Bill b : s.getBills()) {
//				if ()
//			}
//		}
//	}
		
		

	
}
