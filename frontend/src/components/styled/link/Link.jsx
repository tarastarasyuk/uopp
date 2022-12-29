import { Link, withStyles } from '@material-ui/core';

export const StyledLink = withStyles((theme) => ({
    root: {
      color: 'black',
      '&:hover': {
        color: '#3273F6',
        textDecoration: 'none',
        cursor: 'pointer',
      },
    },
  }))(Link);