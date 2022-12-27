import React, { useState, useContext } from 'react'
import { TextField, Button, makeStyles } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { AuthContext } from 'context/auth';
import { editProfile } from 'store/profile/actions';

const useStyles = () => makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
            width: '25ch',
        },
    },
}));

const ProfileEditorForm = () => {
    const { setAuth } = useContext(AuthContext);
    const { student } = useSelector((state) => state.profile);
    const classes = useStyles();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');

    const dispatch = useDispatch();
    
    const submit = (e) => {
        e.preventDefault();
        const user = {
            profileId: student.id,
            firstName,
            lastName,
            email,
            age: +age,
            phone,
            skills: student.skills,
        }
        dispatch(editProfile(user));
        setAuth(true);
    }

    return (
        <form className={classes.root} noValidate autoComplete='off' style={{'display': 'flex', 'flexDirection': 'column', 'width': '300px', 'margin': '0 auto'}}>
            <TextField label='Name' variant='outlined' value={firstName} onChange={(e) => setFirstName(e.target.value)}/>
            <TextField label='Surname' variant='outlined' value={lastName} onChange={(e) => setLastName(e.target.value)}/>
            <TextField label='Email' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            <TextField label='Phone' variant='outlined' value={phone} onChange={(e) => setPhone(e.target.value)}/>
            <TextField label='Age' variant='outlined' value={age} onChange={(e) => setAge(e.target.value)}/>
            <Button type='submit' onClick={submit}>Confirm</Button>
        </form>
    )
}

export { ProfileEditorForm };