import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initialState = {};
const middleware = [thunk];

const ReactReduxDevTools = window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__();

const userAgentChrome = window.navigator.userAgent.includes("Chrome");

let store;

if (userAgentChrome && ReactReduxDevTools) {
  store = createStore(rootReducer, initialState, compose(applyMiddleware(...middleware), ReactReduxDevTools));
} else {
  store = createStore(rootReducer, initialState, compose(applyMiddleware(...middleware)));
}

export default store;
