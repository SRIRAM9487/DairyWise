import axios from "axios";


export const login = async (UserLoginDTO) => {
  try {
    const response = await axios.post("http://localhost:8080/user/login",UserLoginDTO);
    return response;
  } catch (error) {
    console.error(error);
  }
}
