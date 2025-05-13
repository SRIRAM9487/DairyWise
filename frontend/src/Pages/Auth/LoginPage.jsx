import React, { useState } from 'react'
import { login } from '../../Queries/loginQuery';

const LoginPage = () => {

  // Login form
  const [loginData, setloginData] = useState({
    userId: '',
    password: ''
  });

  // Error
  const [error, seterror] = useState('');

  // Update form data
  const handleChange = (e) => {
    const { name, value } = e.target;
    setloginData((prev) => ({
      ...prev,
      [name]: value
    }));
  }

  // User Login
  const handleSubmit = async (e) => {
    e.preventDefault();
    seterror('');
    try {
      await login(loginData);
    } catch (error) {
      console.log(error);
      seterror("Login Failed please try again...")
    }
  }

  // Rest Form
  const handleReset = () => {
    setloginData({
      userId: '',
      password: ''
    });
    seterror("");
  }

  return (

    <div className='flex justify-center items-center h-screen'>
      <form
        onSubmit={handleSubmit}
        onReset={handleReset}
        className='border  w-[500px] p-4'>

        <div className="flex flex-col items-center mt-4 mb-4">
          <h1 className='font-bold text-2xl mb-2'>User Login</h1>
          <div className="w-[50px] h-1 bg-blue-500 rounded-full"></div>
        </div>

        <div className='flex flex-col mt-4'>
          <label htmlFor="userId" className='font-semibold text-lg  '>User Name</label>
          <input
            type="text"
            name="userId"
            id="userId"
            onChange={handleChange}
            value={loginData.userId}
            required
            className='w-full border p-2 mt-2'
            placeholder='Enter Your Name'
          />
        </div>

        <div className='flex flex-col mt-4 mb-4'>
          <label htmlFor="password">password</label>
          <input
            type="text"
            name="password"
            id="password"
            onChange={handleChange}
            value={loginData.password}
            required
            className='w-full border p-2 mt-2'
            placeholder='Enter Your Password'
          />
        </div>

        <div className='flex flex-row p-4 gap-4 '> <button
          type='submit'
          className='w-[200px] h-[50px] border  rounded-[5px]  hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out   bg-blue-500'>
          Login
        </button>
          <button
            type='reset'
            className='w-[200px] h-[50px] border  rounded-[5px]  hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out   bg-blue-500'>
            Cancel
          </button>

        </div>
        {
          error &&
          <p className='text-red-600'>Error : {error}</p>
        }

      </form>
    </div>
  )
}

export default LoginPage;
