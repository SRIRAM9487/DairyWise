import { useState } from 'react';
import TextField from '../../Component/Common/TextField';

const Login = () => {

  const [formData, setformData] = useState({
    userName: '',
    password: ''
  });

  const [errors, seterrors] = useState({});

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
    if (!formData.userName.trim()) {
      console.log("BRUBH")
      newErrors.userName = 'User Name is required';
    }
    if (!formData.password.trim()) {
      newErrors.password = 'Password is required';
    }
    return newErrors;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validateErrors = validate();
    if (Object.keys(validateErrors).length > 0) {
      seterrors(validateErrors);
      return;
    }
    console.log("FORM SUBMITTED");
    console.log(formData);
  };

  return (
    <div className='flex justify-center items-center w-screen h-screen'>

      <form onSubmit={handleSubmit} className='w-[500px] border p-4'>

        <div className="flex flex-col items-center mt-4">
          <h1 className='font-bold text-2xl mb-2'>User Login</h1>
          <div className="w-[50px] h-1 bg-blue-500 rounded-full"></div>
        </div>

        <TextField
          id="userName"
          label="UserName"
          type="text"
          placeholder="Enter User Name"
          required={true}
          value={formData.userName}
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
            className='w-[200px] h-[50px] border border-black rounded-[5px] bg-blue-500 hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out'
            type='submit'>
            SUBMIT
          </button>

          <button
            className='w-[200px] h-[50px] border border-black rounded-[5px] bg-blue-500 hover:bg-blue-600 text-white font-semibold shadow-md transition duration-200 ease-in-out'
            type='reset'
            onClick={() => {
              setformData({ userName: '', password: '' });
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
