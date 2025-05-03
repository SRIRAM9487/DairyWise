import { useMutation } from "@tanstack/react-query"
import AxiosInstance from "./AxiosInstance"

export const login = () => {
  return useMutation("login",
    async (userLoginRequestDTO) => {
      const response = await AxiosInstance.post("/login", userLoginRequestDTO);
      return response.data;
    }
  )
}
