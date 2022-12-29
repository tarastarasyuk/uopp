import { Button, withStyles } from '@material-ui/core';

export const ContainedButton = withStyles((theme) => ({
    root: {
      color: theme.palette.getContrastText('#3273F6'),
      backgroundColor: '#3273F6',
      textTransform: 'capitalize',
      fontWeight: '700',
      '&:hover': {
        backgroundColor: '#1954cb',
      },
    },
  }))(Button);