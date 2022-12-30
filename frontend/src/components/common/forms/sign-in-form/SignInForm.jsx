/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useContext, useState }  from 'react'
import { TextField, Button } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { AuthContext } from 'context/auth';
import { StudentContext } from 'context/student';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { getProfile } from 'store/profile/actions';
import jwtDecode from 'jwt-decode';

const notify = () => toast.success('Confirm your e-mail and sign in!', {
    position: "top-right",
    autoClose: 5000,
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
    theme: "light",
    });

const SignInForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const { student } = useSelector((state) => state.profile);
    const { setAuth } = useContext(AuthContext);
    const { setStudentContext } = useContext(StudentContext);

    const navigate = useNavigate();
    const dispatch = useDispatch();

    useEffect(() => {

        if(student)
            setStudentContext(student);
        
    }, [student]);

    const submit = (e) => {
        e.preventDefault();

        const studentDto = {
            email,
            password,
        }

        axios.post('http://localhost:8080/sign-in', {...studentDto})
        .then(res => {
            const data = jwtDecode(res.data);
            sessionStorage.setItem('id', data.id);
            sessionStorage.setItem('token', res.data);
            dispatch(getProfile({profileId: data.id}));
        });
        
        navigate('/profile');
        setAuth(true);
    }

    return (
        <form className='sign-form' noValidate autoComplete='off'>
            <ToastContainer />
            <div className='input-wrapper'>
                <span>E-mail:</span>
                <TextField className='input' label='Email' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            </div>
            <div className='input-wrapper'>
                <span>Password:</span>
                <TextField className='input' type='password' label='Password' variant='outlined' value={password} onChange={(e) => setPassword(e.target.value)}/>
            </div>
            <Button type='submit' onClick={submit}>Sign in</Button>
        </form>
    )
}

export { SignInForm, notify };