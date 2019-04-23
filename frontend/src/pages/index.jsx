import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import MainPage from "./MainPage";
import Messenger from "./Messenger";
import Tasks from "./Tasks";

// export default class Root extends Component {
//   render() {
//     return (
//       <Router>
//         <Switch>
//           <Route exact path="/" component={MainPage} />
//           <Route path="/tasks" component={Tasks} />
//           <Route path="/order/:direction(asc|desc)" component={Tasks} />
//           <Route path="/messenger" component={Messenger} />
//         </Switch>
//       </Router>
//     );
//   }
// }

function Root() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={MainPage} />
        <Route path="/tasks" component={Tasks} />
        <Route path="/tasks/:id" component={Tasks} />
        <Route path="/messenger" component={Messenger} />
      </Switch>
    </Router>
  );
}

export default Root;
