import { Button, withStyles } from '@material-ui/core';

export const ContainedErrorButton = withStyles((theme) => ({
    root: {
      color: theme.palette.getContrastText('#E24B4B'),
      backgroundColor: '#E24B4B',
      '&:hover': {
        backgroundColor: '#C33333',
      },
    },
  }))(Button);