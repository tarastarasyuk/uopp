import { Home, SignIn, SignUp, Editor, Creator, Profile } from 'pages';

export const privateRoutes = [
    {path: '/', element: <Home />, exact: true},
    {path: '/editor', element: <Editor />, exact: true},
    {path: '/creator', element: <Creator />, exact: true},
    {path: '/profile', element: <Profile />, exact: true}
]

export const publicRoutes = [
    {path: '/', element: <Home />, exact: true},
    {path: '/sign-in', element: <SignIn />, exact: true},
    {path: '/sign-up', element: <SignUp />, exact: true}
]