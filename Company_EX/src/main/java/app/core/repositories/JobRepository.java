package app.core.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByEndDate(Date endDate);
	
	List<Job> findByEndDateBetween(Date date1, Date date2);
}
