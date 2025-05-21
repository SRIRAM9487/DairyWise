package com.DairyWise.backend.User.Config;

public class AppConstant {

  public static final Long ADMIN_ID = 101L;
  public static final Long USER_ID = 102L;
  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
  public static final String[] PUBLIC_URLS = {
      "/swagger-ui/**",
      "/swagger-ui.html",
      "/v3/api-docs",
      "/v3/api-docs/**",
      "/v3/api-docs.yaml",
      "/v3/api-docs/swagger-config",
      "/v1/user/login",
      "/v1/user/register",
  };
  public static final String[] SUDO_URLS = { "/api/v1/sudo/**" };
  public static final String[] ADMIN_URLS = { "/api/v1/admin/**" };
  public static final String[] MANAGER_URLS = { "/api/v1/manager/**" };
}
