import React from 'react';
import {Link} from 'react-router-dom';
import {Helmet} from 'react-helmet';
import PageNotFound from '../../assets/PageNotFound.png';

export const NotFoundPage: React.FunctionComponent = () => (
    <div style={{textAlign: "center"}}>
        <Helmet>
            <title>Page Not Found | Quiz System</title>
        </Helmet>
        <img src={PageNotFound} alt={'Page Not found'}/>
        <h3>
            <Link to="/">Go to Home page</Link>
        </h3>
    </div>
);