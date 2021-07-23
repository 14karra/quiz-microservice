import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import Helmet from "react-helmet";
import {Login} from "./component/page/login";
import Dashboard from "./component/page/dashboard";
import {NotFoundPage} from "./component/page/notFoundPage";

class App extends Component {
    render() {
        return (
            <Router>
                <Helmet>
                    <meta charSet="utf-8"/>
                    <title>Home | Quiz</title>
                    <meta httpEquiv="content-type" content="text/html; charset=utf-8"/>
                    <meta name="keywords" content="Quiz System"/>
                    <meta name="description" content="Front-end platform design for a quiz system."/>
                    <meta name="author" content="Djambong Tenkeu Hank-Debain"/>
                </Helmet>
                <Switch>
                    <Redirect exact path="/" to="/login"/>
                    <Route exact path="/login" component={Login}/>
                    <Route path="/login/**" component={Login}/>
                    <Route exact path="/dashboard" component={Dashboard}/>
                    <Route path="/dashboard/**" component={Dashboard}/>
                    <Route exact path="/404" component={NotFoundPage}/>
                    <Redirect to="/404"/>
                </Switch>
            </Router>
        );
    }
}

export default App;

