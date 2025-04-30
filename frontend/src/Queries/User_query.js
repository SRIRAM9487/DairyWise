import { useQuery } from "@tanstack/react-query"
import AxiosInstance from "./AxiosInstance"

export const login = () => {

  return useQuery("login",
    async () => {
      return await AxiosInstance.post("/login")
    }
  )
}
