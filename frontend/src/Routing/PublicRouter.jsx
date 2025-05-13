import React from 'react'
import { Route } from 'react-router-dom'
import { isLogin } from '../Authentication/auth'

const PublicRouter = ({ component: Component, ...rest }) => {
  return (
    <Route
      {...rest}
      render={
        props => (isLogin() ? <Redirect to="/home" /> : <Component {...props} />)
      }
    />
  )
}

export default PublicRouter
