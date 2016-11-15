package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

	@Query("select ban from Sponsor s join s.campaigns c join c.banners ban where s.id = ?1 and ban.timesShownMonth > 0")
	Collection<Banner> numberOfBanners(int id);
	
}
