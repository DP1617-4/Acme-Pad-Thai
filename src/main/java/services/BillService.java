package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BillRepository;
import security.LoginService;
import security.UserAccount;
import domain.Bill;

@Service
@Transactional
public class BillService {

	//managed repository---------------------
	@Autowired
	private BillRepository billRepository;
	
	//supporting services -------------------
	
	//Basic CRUD methods --------------------
	public Bill create() {
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
	
	public Bill save(Bill bill) {
		Bill saved;
		saved = billRepository.save(bill);
		return saved;
	}
	
	public void delete(Bill bill) {
		billRepository.delete(bill);
	}
	
	//Auxiliary methods ---------------------
	
	//Our other bussiness methods -----------
	public Collection<Double> calculateAvgDevPaidAndUnpaidBills() {
		return billRepository.calculateAvgDevPaidAndUnpaidBills();
	}
	
	public Bill save2(Bill bill) { // Requirement 33.3
		UserAccount sponsor;
		sponsor = LoginService.getPrincipal();
		Assert.isTrue(bill.getSponsor().equals(sponsor));
		Date moment = new Date(System.currentTimeMillis()-100);
		bill.setPaymentDate(moment);
		Bill saved = this.save(bill);
		return saved;
	}
}
