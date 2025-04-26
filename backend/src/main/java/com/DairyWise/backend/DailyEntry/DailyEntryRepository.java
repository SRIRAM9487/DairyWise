package com.DairyWise.backend.DailyEntry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface DailyEntryRepository extends MongoRepository<DailyEntryModel,Long>{
}
