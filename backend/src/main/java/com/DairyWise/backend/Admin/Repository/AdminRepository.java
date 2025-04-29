package com.DairyWise.backend.Admin.Repository;

import com.DairyWise.backend.Admin.Model.AdminModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {

}
