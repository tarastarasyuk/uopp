import React, { useState, useContext, useEffect } from 'react'
import { TextField, Button } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { AuthContext } from 'context/auth';
import { editProfile } from 'store/profile/actions';
import { ContainedButton } from 'components/styled/button';
import { useNavigate } from 'react-router-dom';

const ProfileEditorForm = () => {
    const { setAuth } = useContext(AuthContext);
    const { student } = useSelector((state) => state.profile);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');
    const [gender, setGender] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();
    
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
            gender,
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
            setGender(student.gender);
        }
    }, [student]);

    const logout = (e) => {
        sessionStorage.removeItem('token'); 
        navigate('/'); 
        setAuth(false);
    }

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
                <span>Gender:</span>
                <TextField className='input' label='Gender' variant='outlined' value={email} onChange={(e) => setGender(e.target.value)}/>
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
            <ContainedButton variant='contained' color='primary' onClick={logout}>Logout</ContainedButton>
            <Button type='submit' onClick={submit}></Button>
        </form>
    )
}

export { ProfileEditorForm };