import React, { useState, useContext } from 'react'
import { TextField, Button } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { AuthContext } from 'context/auth';
import { editProfile } from 'store/profile/actions';
import { useEffect } from 'react';

const ProfileEditorForm = () => {
    const { setAuth } = useContext(AuthContext);
    const { student } = useSelector((state) => state.profile);
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

    useEffect(() => {
        if(student) {
            setFirstName(student.firstName);
            setLastName(student.lastName);
            setEmail(student.email);
            setPhone(student.phone);
            setAge(student.age);
        }
    }, [student]);

    return (
        <form className='sign-form' style={{margin: '10px 0'}} noValidate autoComplete='off'>
            <div className='input-wrapper'>
                <span>First name:</span>
                <TextField className='input' label='Name' variant='outlined' value={firstName} onChange={(e) => setFirstName(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Last name:</span>
                <TextField className='input' label='Surname' variant='outlined' value={lastName} onChange={(e) => setLastName(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>E-mail:</span>
                <TextField className='input' label='E-mail' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Phone:</span>
                <TextField className='input' label='Phone' variant='outlined' value={phone} onChange={(e) => setPhone(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Age:</span>
                <TextField className='input' label='Age' variant='outlined' value={age} onChange={(e) => setAge(e.target.value)}/>
            </div>

            <Button type='submit' onClick={submit}>Confirm</Button>
        </form>
    )
}

export { ProfileEditorForm };