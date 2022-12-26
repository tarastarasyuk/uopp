import React from 'react'
import { Radio, TextareaAutosize, FormControl, FormLabel, FormControlLabel, RadioGroup, TextField, Button, makeStyles } from '@material-ui/core';
import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { createOpportunity } from 'store/opportunities-editor/actions';

const useStyles = () => makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
            width: '25ch',
        },
    },
}));

const EditorForm = () => {
    const classes = useStyles();
    const [name, setName] = useState('');
    const [deadline, setDeadline] = useState('');
    const [asap, setAsap] = useState(false);
    const [content, setContent] = useState('');

    const dispatch = useDispatch();
    
    const submit = (e) => {
        e.preventDefault();
        const opportunity = {
            name,
            deadline,
            asap,
            content,
        }
        console.log(opportunity);

        dispatch(createOpportunity(opportunity));
    }

    return (
        <form className={classes.root} noValidate autoComplete='off' style={{'display': 'flex', 'flexDirection': 'column', 'width': '300px', 'margin': '0 auto'}}>
            <TextField label='Name' variant='outlined' value={name} onChange={(e) => setName(e.target.value)}/>
            <TextField variant='outlined' type='date' value={deadline} onChange={(e) => setDeadline(e.target.value)} />
            <FormControl component='fieldset'>
            <FormLabel component='legend'>ASAP</FormLabel>
            <RadioGroup aria-label='asap' name='asap1' value={asap} onChange={(e) => setAsap(e.target.value)} >
                <FormControlLabel value={true} control={<Radio />} label='Yes' />
                <FormControlLabel value={false} control={<Radio />} label='No' />
            </RadioGroup>
            </FormControl>
            <p>Content</p>
            <TextareaAutosize value={content} onChange={(e) => setContent(e.target.value)}></TextareaAutosize>
            <Button type='submit' onClick={submit}>Create</Button>
        </form>
    )
}

export { EditorForm };