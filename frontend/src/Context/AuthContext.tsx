import { createContext, useState, ReactNode } from 'react';

type JwtContextType = {
  jwtToken: string | null;
  setJwtToken: (token: string | null) => void;
};

export const JwtContext = createContext<JwtContextType>({
  jwtToken: null,
  setJwtToken: () => { },
});

export const JwtProvider = ({ children }: { children: ReactNode }) => {
  const [jwtToken, setJwtTokenState] = useState<string | null>(() => {
    return localStorage.getItem('JWT_TOKEN');
  });

  const setJwtToken = (token: string | null) => {
    if (token) {
      localStorage.setItem('JWT_TOKEN', token);
    } else {
      localStorage.removeItem('JWT_TOKEN');
    }
    setJwtTokenState(token);
  };

  return (
    <JwtContext.Provider value={{ jwtToken, setJwtToken }}>
      {children}
    </JwtContext.Provider>
  );
};
