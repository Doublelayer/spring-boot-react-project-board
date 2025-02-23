import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import ProjectBoard from "./components/ProjectBoard";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProjectTask from "./components/ProjectTask/AppProjectTask";
import { Provider } from "react-redux";
import store from "./store";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Navbar />
            <Route exact path="/" component={ProjectBoard} />
            <Route exact path="/addprojecttask" component={AddProjectTask} />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
