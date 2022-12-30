import React, { useState } from 'react';
import { Radio, FormControl, FormLabel, FormControlLabel, RadioGroup, TextField, Button } from '@material-ui/core';
import { useDispatch } from 'react-redux';
import { createOpportunity } from 'store/opportunities-editor/actions';
import { useNavigate } from 'react-router';
import { fetchOpportunitiesFromTelegram } from 'store/opportunities-editor/actions';
import { OutlinedButton } from 'components/styled/button';

const OpportunityCreatorForm = () => {
    const [name, setName] = useState('');
    const [deadline, setDeadline] = useState('');
    const [asap, setAsap] = useState(false);
    const [content, setContent] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();
    
    const submit = (e) => {
        e.preventDefault();

        const isAsap = (asap === 'true');

        const opportunity = {
            name,
            deadline,
            asap: isAsap,
            content,
        }

        dispatch(createOpportunity(opportunity));
        navigate('/');
    }

    return (
        <form className='sign-form' noValidate autoComplete='off' style={{width: '400px'}}>
             <div className='input-wrapper'>
                <span>Opportunity name:</span>
                <TextField required className='input' label='Name' variant='outlined' value={name} onChange={(e) => setName(e.target.value)} style={{background: 'white'}}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Deadline:</span>
                <TextField required className='input' variant='outlined' type='date' value={deadline} onChange={(e) => setDeadline(e.target.value)} style={{background: 'white'}} />
            </div>

            <div className='input-wrapper'>
                <span>Deadline:</span>

                <FormControl required component='fieldset'>
                <FormLabel component='legend'>ASAP</FormLabel>
                <RadioGroup className='input' row aria-label='asap' name='asap1' value={asap} onChange={(e) => setAsap(e.target.value)} style={{display: 'flex'}}>
                    <FormControlLabel value={'true'} control={<Radio />} label='Yes' />
                    <FormControlLabel value={'false'} control={<Radio />} label='No' />
                </RadioGroup>
                </FormControl>
            </div>

            <div className='input-wrapper'>
                <span>Content:</span>
                <TextField
                    required
                    className='input'
                    id="outlined-multiline-static"
                    multiline
                    minRows={4}
                    value={content} 
                    onChange={(e) => setContent(e.target.value)} 
                    style={{borderRadius: '5px', background: 'white'}}
                    variant="outlined"
                />
            </div>
            
            <Button type='submit' onClick={submit}>Create</Button>
        <OutlinedButton style={{marginRight: '10px'}} variant='outlined' color='primary' onClick={(e) => dispatch(fetchOpportunitiesFromTelegram())}>Get opportunities</OutlinedButton>
        </form>
    )
}

export { OpportunityCreatorForm };