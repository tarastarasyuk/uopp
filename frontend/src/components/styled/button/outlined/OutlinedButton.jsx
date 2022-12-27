import { Button, withStyles } from '@material-ui/core';

export const OutlinedButton = withStyles((theme) => ({
    root: {
      backgroundColor: theme.palette.getContrastText('#594BFF'),
      color: '#594BFF',
      border: '1px solid #594BFF',
      '&:hover': {
        color: '#3E33C2',
        border: '1px solid #3E33C2',
      },
    },
  }))(Button);