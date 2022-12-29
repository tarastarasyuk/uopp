import { Button, withStyles } from '@material-ui/core';

export const OutlinedButton = withStyles((theme) => ({
    root: {
      backgroundColor: theme.palette.getContrastText('#3273F6'),
      color: '#3273F6',
      border: '1px solid #3273F6',
      textTransform: 'capitalize',
      fontWeight: '700',
      '&:hover': {
        color: '#1954cb',
        border: '1px solid #1954cb',
      },
    },
  }))(Button);