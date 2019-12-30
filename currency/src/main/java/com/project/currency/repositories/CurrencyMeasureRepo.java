package com.project.currency.repositories;

import com.project.currency.models.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyMeasureRepo extends JpaRepository<HistoryModel, Long> {
}
