import React, { useEffect, useState } from 'react';
import { Radio, FormControl, FormLabel, FormControlLabel, RadioGroup, TextField, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import moment from 'moment/moment';
import { ContainedErrorButton, OutlinedButton } from 'components';
import { fetchOpportunities, deleteOpportunity, editOpportunity } from 'store/opportunities-editor/actions';

const OpportunitiesEditorForm = () => {

    const [name, setName] = useState('');
    const [deadline, setDeadline] = useState('');
    const [asap, setAsap] = useState(false);
    const [content, setContent] = useState('');
    const [editing, setEditing] = useState(false);
    const [id, setId] = useState();

    const dispatch = useDispatch();
    const { opportunities } = useSelector((state) => state.opportunitiesEditor);

    useEffect(() => {
        dispatch(fetchOpportunities());
    }, [dispatch]);

    const submit = (e) => {
        e.preventDefault();
        setEditing(false);
        
        const opportunity = {
            id,
            name,
            deadline,
            asap,
            content,
        }
        
        dispatch(editOpportunity(opportunity));
    }
    
    const editClicked = (e) => {
        e.preventDefault();
        setEditing(true);
        setId(e.target.closest('.opportunity-row').getAttribute('opportunity-id'));
        setName(e.target.closest('.opportunity-row').getAttribute('opportunity-name'));
        setDeadline(e.target.closest('.opportunity-row').getAttribute('opportunity-deadline'));
        setAsap(e.target.closest('.opportunity-row').getAttribute('opportunity-asap') === 'true');
        setContent(e.target.closest('.opportunity-row').getAttribute('opportunity-content'));
    }

    const deleteClicked = (e) => {
        e.preventDefault();
        const id = e.target.closest('.opportunity-row').getAttribute('opportunity-id');
        setId(id);
        dispatch(deleteOpportunity({id}));
    }

    return (
        <>
        {editing 
        ? 
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
                <RadioGroup className='input' row aria-label='asap' name='asap1' value={asap.toString()} onChange={(e) => setAsap(e.target.value)} style={{display: 'flex'}}>
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
            
            <Button type='submit' onClick={submit}>Edit</Button>
        </form>
        :
        <form className='sign-form' noValidate autoComplete='off' style={{width: '700px'}}>
             <TableContainer>
                <Table aria-label="simple table">
                    <TableHead>
                    <TableRow>
                        <TableCell>Name</TableCell>
                        <TableCell align='right'>Deadline</TableCell>
                        <TableCell align='right'>ASAP</TableCell>
                        <TableCell align='right'>Caption</TableCell>
                        <TableCell align='right'>Edit</TableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {opportunities.map((opportunity) => (
                        <TableRow key={opportunity.id} className='opportunity-row' 
                        opportunity-id={opportunity.id} 
                        opportunity-name={opportunity.name}
                        opportunity-deadline={moment.utc(opportunity.deadline).format('YYYY-MM-DD')}
                        opportunity-asap={opportunity.asap.toString()}
                        opportunity-content={opportunity.content}>
                            <TableCell component='th' scope='row'>
                                {opportunity.name}
                            </TableCell>
                            <TableCell align='right'>{moment.utc(opportunity.deadline).format('YYYY-MM-DD')}</TableCell>
                            <TableCell align='right'>{opportunity.asap ? 'yes' : 'no'}</TableCell>
                            <TableCell align='right'>{opportunity.content}</TableCell>
                            <TableCell align='right'>
                                <OutlinedButton variant='contained' color='primary' onClick={editClicked} style={{marginRight: '10px'}}>Edit</OutlinedButton>  
                                <ContainedErrorButton variant='contained' color='primary' onClick={deleteClicked}>Delete</ContainedErrorButton>   
                            </TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </form>
        }
        </>
        
    )
}

export { OpportunitiesEditorForm };