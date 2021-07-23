import React, {Component} from 'react'
import {LogoOption} from "./option/logoOption";
import {LogoutOption} from "./option/logoutOption";

interface IProps {
}

interface IState {
}

export class DashboardNavbar extends Component<IProps, IState> {
    constructor(props: IProps) {
        super(props);
    }

    render() {
        return (
            <div>
                <nav className="navbar topnav navbar-expand-sm bg-dark navbar-dark">
                    <LogoOption/>
                    <div className="navbar-nav ml-auto">
                        <LogoutOption/>
                    </div>
                </nav>
            </div>
        )
    }
}