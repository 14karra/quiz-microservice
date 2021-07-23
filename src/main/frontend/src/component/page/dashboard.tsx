import React from 'react'
import {Helmet} from 'react-helmet';
import {DashboardNavbar} from "../navbar/dashboardNavbar";
export default class Dashboard extends React.Component {
    constructor(props : any) {
        super(props);
    }

    render() {
        return (
            <div>
                <Helmet>
                    <title>Dashboard | Quiz System</title>
                </Helmet>
                <DashboardNavbar/>
            </div>
        )
    }
}