package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

	@Query("select avg(b.cost), stddev(b.cost), (select avg(b.cost) from Bill b where b.paymentDate is null), (select stddev(b.cost) from Bill b where b.paymentDate is null) from Bill b where b.paymentDate is not null")
	Double[][][] calculateAvgDevPaidAndUnpaidBills();

	@Query(" select sum(ban.timesShownMonth*sys.fee) from Sponsor s join s.campaigns c join c.banners ban, SystemConfiguration sys group by s")
	Collection<Double> computeBillCost();
}
