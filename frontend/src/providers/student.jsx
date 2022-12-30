import { StudentContext } from 'context/student';
import React, { useState } from 'react';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getProfile } from 'store/profile/actions';

const StudentProvider = ({ children }) => {
    const dispatch = useDispatch();
    const { student } = useSelector((state) => state.profile);

    useEffect(() => {
        dispatch(getProfile({
            profileId: sessionStorage.getItem('id'),
        }));
    }, [dispatch]);

    const [studentContext, setStudentContext] = useState(student);
    
    const studentProvidedValue = { studentContext, setStudentContext };
    
    return (
        <StudentContext.Provider value={studentProvidedValue}>{children}</StudentContext.Provider>
    )
}

export { StudentProvider };