import axios from "axios";

const AxiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "Application/json",
    Accept: "Application/json",
  }
});

AxiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("JWT_TOKEN");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }

);

export default AxiosInstance;
