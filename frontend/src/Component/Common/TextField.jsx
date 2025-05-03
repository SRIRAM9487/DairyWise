import React from 'react'

const TextField = ({
  id,
  label,
  type,
  placeholder,
  minLength,
  onChange,
  required,
  value,
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
        value={value}
        placeholder={placeholder}
        minLength={minLength}
        className={`w-full border p-2 mt-2`}
        required={required}
        onChange={onChange}
      />

      {
        errors[id] && errors[id] && (
          <p className='text-sm text-red-700 mt-1'>
            {errors[id]}
          </p>
        )
      }

    </div>
  )
}

export default TextField; 
