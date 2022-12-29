import { Button, withStyles } from '@material-ui/core';
import { theme as customTheme } from 'public/themes';

export const ContainedButton = withStyles((theme) => ({
    root: {
      color: theme.palette.getContrastText(customTheme.colors.blue),
      backgroundColor: customTheme.colors.blue,
      textTransform: 'capitalize',
      fontWeight: '700',
      '&:hover': {
        backgroundColor: customTheme.colors.blueHover,
      },
    },
  }))(Button);