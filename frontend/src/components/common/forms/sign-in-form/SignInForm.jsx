import React, { useEffect, useContext, useState }  from 'react'
import { TextField, Button } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { getStudent } from 'store/student/actions';
import { useNavigate } from 'react-router';
import { AuthContext } from 'context/auth';
import { DataStatus } from 'common/enums';
import { StudentContext } from 'context/student';

const SignInForm = () => {
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
            sessionStorage.setItem('id', student.id);
            setAuth(true);
            setStudentContext(student);
        }

    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [student]);

    return (
        <form className='sign-form' noValidate autoComplete='off'>
            <div className='input-wrapper'>
                <span>E-mail:</span>
                <TextField className='input' label='Email' variant='outlined' value={email} onChange={(e) => setEmail(e.target.value)}/>
            </div>
            <Button type='submit' onClick={submit}>Sign in</Button>
        </form>
    )
}

export { SignInForm };