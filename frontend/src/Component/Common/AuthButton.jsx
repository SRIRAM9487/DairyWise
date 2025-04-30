import React from 'react';

function AuthButton({
  normal = 'bg-blue-500',
  hover = 'hover:bg-blue-600',
  focus = 'focus:ring-2 focus:ring-blue-300',
  name = 'Click Me',
  onClick,
  type = 'button',
}) {
  return (
    <button
      onClick={onClick}
      type={type}
      className={`w-52 h-12 rounded-md border border-black text-white font-semibold shadow-md transition duration-200 ease-in-out focus:outline-none ${normal} ${hover} ${focus}`}
    >
      {name}
    </button>
  );
}

export default AuthButton;
