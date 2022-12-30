import React, { useState, useEffect } from 'react';
import { TextField, Button } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { createStudent } from 'store/student/actions';
import { useNavigate } from 'react-router-dom';
import '../style.css';
import { DataStatus } from 'common/enums';

const genders = [
    {
      value: 'MALE',
      label: 'male',
    },
    {
      value: 'FEMALE',
      label: 'female',
    },
  ];

const SignUpForm = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');
    const [gender, setGender] = useState('MALE');

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { student, status } = useSelector((state) => state.student);
    
    const submit = (e) => {
        e.preventDefault();
        const user = {
            firstName,
            lastName,
            email,
            password,
            age: +age,
            phone,
            skills: [],
            gender,
        }
        
        dispatch(createStudent(user));
    }

    useEffect(() => {
        if(student){
            sessionStorage.setItem('id', student.id);
            navigate('/sign-in');
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [student]);

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
                <span>Password:</span>
                <TextField required type='password' className='input' label='Password' variant='outlined' value={password} onChange={(e) => setPassword(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Gender:</span>
                <TextField
                    required
                    className='input'
                    id="outlined-select-currency-native"
                    select
                    label="Gender"
                    value={gender}
                    onChange={(e) => setGender(e.target.value)}
                    SelectProps={{
                        native: true,
                    }}
                    variant="outlined">
                    {genders.map((option) => (
                        <option key={option.value} value={option.value}>
                            {option.label}
                        </option>
                    ))}
                </TextField>         
            </div>

            <div className='input-wrapper'>
                <span>Phone:</span>
                <TextField required className='input' label='Phone' variant='outlined' value={phone} onChange={(e) => setPhone(e.target.value)}/>
            </div>
            
            <div className='input-wrapper'>
                <span>Age:</span>
                <TextField required className='input' label='Age' variant='outlined' value={age} onChange={(e) => setAge(e.target.value)}/>
            </div>
            
            {status === DataStatus.PENDING
                ? <div className="lds-ring"><div></div><div></div><div></div><div></div></div>
                : <Button type='submit' onClick={submit}>Sign up</Button>
            }

        </form>
    )
}

export { SignUpForm };