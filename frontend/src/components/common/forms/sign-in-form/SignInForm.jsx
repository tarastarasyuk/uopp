import React, { useEffect, useContext }  from 'react'
import { TextField, Button, makeStyles } from '@material-ui/core';
import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getStudent } from 'store/student/actions';
import { useNavigate } from 'react-router';
import { AuthContext } from 'context/auth';
import { DataStatus } from 'common/enums';
import { StudentContext } from 'context/student';

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

    const { student, status } = useSelector((state) => state.student);
    const { setAuth } = useContext(AuthContext);
    const { setStudentContext } = useContext(StudentContext);

    const dispatch = useDispatch();
    const navigate = useNavigate();
    
    const submit = (e) => {
        e.preventDefault();

        const studentGet = {
            email,
        }

        dispatch(getStudent(studentGet));
    }

    useEffect(() => {
        if(status === DataStatus.SUCCESS){
            navigate('/');
            sessionStorage.setItem('id', student.student.id);
            setAuth(true);
            setStudentContext(student);
        }

    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [student]);

    return (
        <form className={classes.root} noValidate autoComplete='off' style={{'display': 'flex', 'flexDirection': 'column', 'width': '300px', 'margin': '0 auto'}}>
            <TextField label='Email' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            <Button type='submit' onClick={submit}>Sign in</Button>
        </form>
    )
}

export { SignInForm };