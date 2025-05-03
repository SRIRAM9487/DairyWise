  return (
    <div className='flex justify-center items-center w-screen h-screen'>
      <form onSubmit={handleSubmit} className='w-[500px] border p-4'>
        <div className='flex flex-col items-center mt-4'>
          <h1 className='font-bold text-2xl mb-2'>User Login</h1>
          <div className='w-[50px] h-1 bg-blue-500 rounded-full'></div>
        </div>

        <TextField
          id='name'
          label='UserName'
          type='text'
          placeholder='Enter User Name'
          required={true}
          value={formData.name}
          onChange={handleChange}
          errors={errors}
        />

        <TextField
          id='password'
          label='Password'
          type='password'
          placeholder='Enter password'
          required={true}
          value={formData.password}
          onChange={handleChange}
          errors={errors}
        />

        <div className='flex flex-row gap-4 justify-center items-center mt-8'>
          <button
            type='submit'
            className='w-[200px] h-[50px] border border-black rounded-[5px] bg-blue-500 hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out'
          >
            SUBMIT
          </button>
          <button
            type='reset'
            className='w-[200px] h-[50px] border border-black rounded-[5px] bg-blue-500 hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out'
            onClick={() => {
              setFormData({ name: '', password: '' });
              setErrors({});
            }}
          >
            CANCEL
          </button>
        </div>
      </form>
    </div>
  );
}

export default Login;
