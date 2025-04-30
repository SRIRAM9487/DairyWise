import React from 'react'

const TextField = ({
  id,
  label,
  type,
  placeholder,
  minLength,
  register,
  required,
  message,
  errors,
}) => {
  return (
    <div className='flex flex-col mt-4'>

      <label
        htmlFor={id}
        className={`font-semibold text-lg text-gray-800 transition-all duration-300 hover:text-gray-900 w-full`}>
        {label}
      </label>


      <input
        type={type}
        name={id}
        id={id}
        placeholder={placeholder}
        minLength={minLength}
        className={`w-full border p-2 mt-2`}
        {
        ...register(id, {
          required: { value: required, message: message },
          minLength: minLength && { value: minLength, message: `Minimum length is ${minLength}` }
        })}
      />

      {
        errors[id] && (
          <p className=''>
            {errors[id].messagge}
          </p>
        )
      }

    </div>
  )
}

export default TextField; 
