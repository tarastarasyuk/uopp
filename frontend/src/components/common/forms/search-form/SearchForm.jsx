import React from 'react';
import { InputAdornment, TextField } from '@material-ui/core';
import { Search } from '@material-ui/icons';
import { OutlinedButton } from 'components/styled/button';
import { useDispatch } from 'react-redux';
import { fetchOpportunitiesFromTelegram } from 'store/opportunities-editor/actions';

const sortings = [
    {
      value: '',
      label: '',
    },
    {
      value: 'newest',
      label: 'New',
    },
    {
      value: 'asap',
      label: 'ASAP',
    },
    {
      value: 'deadline-soon',
      label: 'Deadline',
    },
  ];
  
const SearchForm = ({sort, setSort}) => {
    
  const dispatch = useDispatch();

  const sorting = (e) => {
    setSort(e.target.value);
  }
  
  return (
    <form style={{display: 'flex', justifyContent: 'center', margin: '20px auto 10px'}}>
        <TextField required className='input' variant='outlined' /*value={deadline} onChange={(e) => setDeadline(e.target.value)}*/ style={{background: 'white', marginRight: '10px', width: '30%'}} InputProps={{
          startAdornment: (
            <InputAdornment position='end'>
              <Search onClick={(e) => console.log('Searching...')} style={{cursor: 'pointer'}}/>
            </InputAdornment>
          )
        }}/>
        <TextField
            style={{width: '10%'}}
            id="outlined-select-currency-native"
            select
            label="Sorting"
            value={sort}
            onChange={sorting}
            SelectProps={{
              native: true,
            }}
            variant="outlined"
          >
          {sortings.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </TextField>
        <OutlinedButton style={{marginRight: '10px'}} variant='outlined' color='primary' onClick={(e) => dispatch(fetchOpportunitiesFromTelegram())}>Get opportunities</OutlinedButton>
    </form>
  )
}

export { SearchForm };