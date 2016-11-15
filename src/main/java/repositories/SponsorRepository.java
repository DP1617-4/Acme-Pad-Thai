package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
	
	@Query("select min(s.campaigns.size), avg(s.campaigns.size), max(s.campaigns.size) from Sponsor s")
	Double[][][] calculateMinAvgMaxFromCampaignsOfSponsors();
	
	@Query("select min(s.campaigns.size), avg(s.campaigns.size), max(s.campaigns.size) from Sponsor s join s.campaigns c where CURRENT_DATE between c.startDate and c.endDate")
	Double[][][] calculateMinAvgMaxFromCampaignsOfSponsorsByDate();
	
	@Query("select s.companyName from Sponsor s group by s.companyName order by s.campaigns.size DESC")
	Collection<String> findCompaniesNameOfSponsors();
	
	@Query("select s.companyName from Sponsor s left join s.bills b group by s.companyName order by sum(b.cost) DESC")
	Collection<String> findCompaniesNameOfSponsorsByBills();
	
	@Query("select s from Sponsor s join s.campaigns c where datediff(current_date, c.endDate) > 90 and current_date not between c.startDate and c.endDate")
	Collection<Sponsor> findInnactiveSponsorInThreeMonths();
	
	@Query("select s.companyName from Sponsor s join s.bills b where b.paymentDate is not null and b.cost < (select avg(b.cost) from Bill b where b.paymentDate is not null) group by s.companyName")
	Collection<String> findCompaniesNameWhichSpentLessInCampaignsThanAvg();
	
	@Query("select s.companyName from Sponsor s join s.bills b where b.paymentDate is not null group by s.companyName having sum(b.cost) >= (select sum(ban.timesShown*sys.fee) from Campaign c join c.banners ban, SystemConfiguration sys group by c having sum(ban.timesShown*sys.fee) >= all(select sum(ban.timesShown*sys.fee) from Campaign c join c.banners ban, SystemConfiguration sys))*(0.9)")
	Collection<String> findCompaniesNameSpent90Percent();
	
	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findByPrincipal(int userAccountId);
}
