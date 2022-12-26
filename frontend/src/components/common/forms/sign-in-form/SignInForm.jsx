import React from 'react'
import { TextField, Button, makeStyles } from '@material-ui/core';
import { useState } from 'react';
// import { useDispatch } from 'react-redux';

const useStyles = () => makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
            width: '25ch',
        },
    },
}));

const SignInForm = () => {
    const classes = useStyles();
    const [email, setEmail] = useState('');

    // const dispatch = useDispatch();
    
    const submit = (e) => {
        e.preventDefault();
        // const user = {
            
        // }
        
        // dispatch(createStudent(user));
    }

    return (
        <form className={classes.root} noValidate autoComplete='off'>
            <TextField label='Email' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            <Button type='submit' onClick={submit}>Sign up</Button>
        </form>
    )
}

export { SignInForm };