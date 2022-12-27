import { OpportunityCreatorForm } from 'components/common/forms/opportunity-creator-form/OpportunityCreatorForm';
import { Layout } from 'components/layout/Layout';
import React from 'react'

const Creator = () => {
  return (
    <div>
      <Layout />
      <OpportunityCreatorForm />
    </div>
  )
}

export { Creator };