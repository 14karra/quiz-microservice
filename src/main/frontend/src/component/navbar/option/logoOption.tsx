import React from 'react'
import {NavLink} from "react-router-dom";
import {CompanyLogo} from "../../companyLogo";

export const LogoOption: React.FunctionComponent = () => {
    return (
        <div>
            <NavLink to="/" className="navbar-brand img-fluid">
                <CompanyLogo/>
            </NavLink>
        </div>
    )
};