import { Button, withStyles } from '@material-ui/core';
import { theme as customTheme } from 'public/themes';

export const ContainedErrorButton = withStyles((theme) => ({
    root: {
      color: theme.palette.getContrastText(customTheme.colors.error),
      backgroundColor: customTheme.colors.error,
      textTransform: 'capitalize',
      fontWeight: '700',
      '&:hover': {
        backgroundColor: customTheme.colors.errorHover,
      },
    },
  }))(Button);