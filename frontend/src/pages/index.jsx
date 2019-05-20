import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import MainPage from "./MainPage";
import Messenger from "./Messenger";
import Tasks from "./Tasks";
import PrivateRoute from "./PageWrapper";

const Root = () => {
  return (
    <Router>
      <Switch>
        <PrivateRoute exact path="/" component={MainPage} />
        <Route exact path="/login" component={MainPage} />
        <PrivateRoute path="/tasks/:id" component={Tasks} />
        <PrivateRoute path="/messenger" component={Messenger} />
        <PrivateRoute path="/" component={MainPage} />
      </Switch>
    </Router>
  );
};

export default Root;
