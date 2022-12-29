import { Link, withStyles } from '@material-ui/core';
import { theme as customTheme } from 'public/themes';

export const StyledLink = withStyles((theme) => ({
    root: {
      color: 'black',
      '&:hover': {
        color: customTheme.colors.blueHover,
        textDecoration: 'none',
        cursor: 'pointer',
      },
    },
  }))(Link);