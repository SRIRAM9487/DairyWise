import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import TextField from '../../Component/Common/TextField';

function Login() {

  const { register, handleSubmit, formState: { errors } } = useForm();

  const onSubmit = () => {

  }

  return (
    <div className='flex justify-center items-center w-screen h-screen'>

      <form onSubmit={handleSubmit(onSubmit)} className=' w-[500px] 
h-[] border p-4'>

        <div className="flex flex-col items-center mt-4 ">
          <h1 className='font-bold text-2xl mb-2'>User Login</h1>
          <div className="w-[50px] h-1 bg-blue-500 rounded-full"></div>
        </div>

        <TextField
          id="name"
          label="UserName"
          type="text"
          placeholder="Enter User Name"
          register={register}
          required={true}
          message="User Name Invalid"
          errors={errors}
        />
        <TextField
          id="password"
          label="Password"
          type="text"
          placeholder="Enter password"
          register={register}
          required={true}
          message="Invalid password"
          errors={errors}
        />
        <div className='flex flex-row gap-4 justify-center items-center mt-8' >
          <button className='w-[200px] h-[50px] border border-black rounded-[5px] bg-blue-500 hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out'
          >
            SUBMIT
          </button>
          <button className='w-[200px] h-[50px] border border-black rounded-[5px] bg-blue-500 hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out'
          >
            CANCEL
          </button>
        </div>
      </form>
    </div>
  )
}

export default Login
