package com.msa.timeline.repository;

import com.msa.timeline.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

}
