import React from 'react'
import { isLogin } from '../Authentication/auth';
import { Route } from 'react-router-dom';

function privateRouter({ comoponent: Component, ...rest }) {
  return (
    <Route
      {...rest}
      render={props => (isLogin() ? <Component {...props} /> : <Redirect to="/" />)}
    />
  );
};


