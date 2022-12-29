import { Link, withStyles } from '@material-ui/core';

export const StyledLink = withStyles((theme) => ({
    root: {
      color: 'black',
      '&:hover': {
        color: '#594BFF',
        textDecoration: 'none',
        cursor: 'pointer',
      },
    },
  }))(Link);