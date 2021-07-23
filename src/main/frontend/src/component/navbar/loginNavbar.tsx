import React from 'react'
import {LogoOption} from "./option/logoOption";

export const LoginNavbar: React.FunctionComponent = () => {
    return (
        <div>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
                <LogoOption/>
            </nav>
        </div>
    )
};