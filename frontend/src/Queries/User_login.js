import AxiosInstance from "./AxiosInstance"

export const login = async (userLoginRequestDTO) => {
  try {
    const response = await AxiosInstance.post("/user/login", userLoginRequestDTO);
    return response.data;
  } catch (error) {
    throw new Error(error || 'Login failed');
  }
}
