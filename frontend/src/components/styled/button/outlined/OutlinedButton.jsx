import { Button, withStyles } from '@material-ui/core';
import { theme as customTheme } from 'public/themes';

export const OutlinedButton = withStyles((theme) => ({
    root: {
      backgroundColor: customTheme.colors.yellow,
      color: customTheme.colors.blue,
      border: `1px solid ${customTheme.colors.blue}`,
      textTransform: 'capitalize',
      fontWeight: '700',
      '&:hover': {
        color: customTheme.colors.blueHover,
        border: `1px solid ${customTheme.colors.blueHover}`,
      },
    },
  }))(Button);