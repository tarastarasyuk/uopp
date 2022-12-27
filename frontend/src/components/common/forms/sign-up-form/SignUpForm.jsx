import React, { useContext } from 'react';
import { TextField, Button, makeStyles } from '@material-ui/core';
import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { createStudent } from 'store/student/actions';
import { AuthContext } from 'context/auth';
import { useNavigate } from 'react-router-dom';

const useStyles = () => makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
            width: '25ch',
        },
    },
}));

const SignUpForm = () => {
    const { setAuth } = useContext(AuthContext);
    const classes = useStyles();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();
    
    const submit = (e) => {
        e.preventDefault();
        const user = {
            firstName,
            lastName,
            email,
            age: +age,
            phone,
            skills: [],
        }
        
        dispatch(createStudent(user));
        navigate('/');
        // sessionStorage.setItem('auth', true);
        setAuth(true);
    }

    return (
        <form className={classes.root} noValidate autoComplete='off' style={{'display': 'flex', 'flexDirection': 'column', 'width': '300px', 'margin': '0 auto'}}>
            <TextField label='Name' variant='outlined' value={firstName} onChange={(e) => setFirstName(e.target.value)}/>
            <TextField label='Surname' variant='outlined' value={lastName} onChange={(e) => setLastName(e.target.value)}/>
            <TextField label='Email' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            <TextField label='Phone' variant='outlined' value={phone} onChange={(e) => setPhone(e.target.value)}/>
            <TextField label='Age' variant='outlined' value={age} onChange={(e) => setAge(e.target.value)}/>
            <Button type='submit' onClick={submit}>Sign up</Button>
        </form>
    )
}

export { SignUpForm };