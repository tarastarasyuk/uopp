import { SignInForm } from 'components/common/sign-in-form/SignInForm';
import { Layout } from 'components/layout/Layout';
import React from 'react';

const SignIn = () => {
  return (
    <div>
      <Layout />
      <SignInForm />
    </div>
  )
}

export { SignIn };