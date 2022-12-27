import { OpportunitiesEditorForm } from 'components/common/forms/opportunities-editor-form/OpportunityCreatorForm';
import { Layout } from 'components/layout/Layout';
import React from 'react';

const Editor = () => {
  return (
    <div>
        <Layout />
        <OpportunitiesEditorForm />
    </div>
  )
}

export { Editor };