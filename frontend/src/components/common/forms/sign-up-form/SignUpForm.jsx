import React, { useContext, useState, useEffect } from 'react';
import { TextField, Button } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { createStudent } from 'store/student/actions';
import { AuthContext } from 'context/auth';
import { useNavigate } from 'react-router-dom';
import '../style.css';

const SignUpForm = () => {
    const { setAuth } = useContext(AuthContext);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { student } = useSelector((state) => state.student);
    
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
        setAuth(true);
    }

    useEffect(() => {
        if(student){
            sessionStorage.setItem('id', student.id);
            navigate('/');
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [student])

    return (
        <form className='sign-form' noValidate autoComplete='off'>
            <div className='input-wrapper'>
                <span>First name:</span>
                <TextField required className='input' label='Name' variant='outlined' value={firstName} onChange={(e) => setFirstName(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Last name:</span>
                <TextField required className='input' label='Surname' variant='outlined' value={lastName} onChange={(e) => setLastName(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>E-mail:</span>
                <TextField required className='input' label='E-mail' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Phone:</span>
                <TextField required className='input' label='Phone' variant='outlined' value={phone} onChange={(e) => setPhone(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Age:</span>
                <TextField required className='input' label='Age' variant='outlined' value={age} onChange={(e) => setAge(e.target.value)}/>
            </div>
            
            <Button type='submit' onClick={submit}>Sign up</Button>
        </form>
    )
}

export { SignUpForm };