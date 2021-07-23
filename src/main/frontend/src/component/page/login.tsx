import React from 'react'
import {Helmet} from 'react-helmet';
import '../../styles/loginPage.css';
import {LoginNavbar} from "../navbar/loginNavbar";
import {LoginBox} from "../loginBox";

export const Login: React.FunctionComponent = () => (
    <div id="loginPageContainer">
        <Helmet>
            <title>Login | Quiz system</title>
        </Helmet>
        <LoginNavbar/>
        <LoginBox />
    </div>
);