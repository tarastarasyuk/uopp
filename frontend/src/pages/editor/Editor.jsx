import { OpportunityEditorForm } from 'components/common/forms/opportunity-editor-form/OpportunityEditorForm';
import { Layout } from 'components/layout/Layout';
import React from 'react'

const Editor = () => {
  return (
    <div>
      <Layout />
      <OpportunityEditorForm />
    </div>
  )
}

export { Editor };