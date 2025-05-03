import { useState } from 'react';
import TextField from '../../Component/Common/TextField';
import { login } from '../../Queries/User_login';

const Login = () => {


  const [formData, setformData] = useState({
    userId: '',
    password: ''
  });

  const [errors, seterrors] = useState({});
  const [loading, setloading] = useState(false);

  const handleChange = (e) => {

    const { name, value } = e.target;

    setformData((prev) => ({
      ...prev,
      [name]: value
    }));

    seterrors({});

  };

  const validate = () => {
    const newErrors = {};
    if (!formData.userId.trim()) {
      newErrors.userId = 'User Name is required';
    }
    if (!formData.password.trim()) {
      newErrors.password = 'Password is required';
    }
    return newErrors;
  };

  const handleSubmit = async (e) => {

    e.preventDefault();

    const validateErrors = validate();

    if (Object.keys(validateErrors).length > 0) {
      seterrors(validateErrors);
      return;
    }

    try {
      setloading(true)
      const response = await login(formData);
    } catch (error) {
      console.error(error);
    } finally {
      setloading(false);
    }

  };

  return (
    <div className='flex justify-center items-center w-screen h-screen'>

      <form onSubmit={handleSubmit} className={`w-[500px] border p-4  ${loading ? "border-blue-400 animate-pulse " : "border-gray-200"}`}>

        <div className="flex flex-col items-center mt-4">
          <h1 className='font-bold text-2xl mb-2'>User Login</h1>
          <div className="w-[50px] h-1 bg-blue-500 rounded-full"></div>
        </div>

        <TextField
          id="userId"
          label="UserName"
          type="text"
          placeholder="Enter User Name"
          required={true}
          value={formData.userId}
          onChange={handleChange}
          errors={errors}
        />

        <TextField
          id="password"
          label="Password"
          type="text"
          placeholder="Enter password"
          required={true}
          value={formData.password}
          onChange={handleChange}
          errors={errors}
        />

        <div className='flex flex-row gap-4 justify-center items-center mt-8'>

          <button
            className={`w-[200px] h-[50px] border border-black rounded-[5px]  hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out   bg-blue-500`}
            type='submit'>
            SUBMIT
          </button>

          <button
            className='w-[200px] h-[50px] border border-black rounded-[5px] bg-blue-500 hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out'
            type='reset'
            onClick={() => {
              setformData({ userId: '', password: '' });
              seterrors({});
            }}>
            CANCEL
          </button>

        </div>

      </form>

    </div>
  );
}

export default Login;
