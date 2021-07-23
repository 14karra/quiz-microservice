import React from 'react'
import companyLogo from "../assets/logoSolutionFabric.jpg";

export const CompanyLogo: React.FunctionComponent = () => {
    return (
        <img src={companyLogo} id='logo_SF' alt="Solution Fabrics logo" style={{width: '70px'}}/>
    )
};
