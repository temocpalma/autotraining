import { createStore, applyMiddleware, combineReducers } from 'redux';
import thunk from 'redux-thunk';
import * as reducers from '../reducers/reducers-main';

const logger = store => next => action => {
  console.log('Dispatching', action);
  let result = next(action);
  console.log('Next State', store.getState());
  return result;
}

export default createStore(combineReducers(reducers), applyMiddleware(logger, thunk));