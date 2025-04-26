package com.DairyWise.backend.DailyEntry;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Entry")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyEntryModel {
  
  @Id
  private Long id;


  private String name;
}
