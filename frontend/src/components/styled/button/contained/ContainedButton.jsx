import { Button, withStyles } from '@material-ui/core';

export const ContainedButton = withStyles((theme) => ({
    root: {
      color: theme.palette.getContrastText('#594BFF'),
      backgroundColor: '#594BFF',
      '&:hover': {
        backgroundColor: '#3E33C2',
      },
    },
  }))(Button);