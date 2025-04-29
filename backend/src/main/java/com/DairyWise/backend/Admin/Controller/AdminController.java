package com.DairyWise.backend.Admin.Controller;

import com.DairyWise.backend.Admin.Service.AdminServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminServiceImp adminServiceImp;


}
