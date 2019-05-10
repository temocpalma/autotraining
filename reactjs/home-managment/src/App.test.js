import React from 'react';
import App from './App';
import { shallow, render, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import { Provider } from 'react-redux';
import store from './components/store';
import ECommerce from './components/main/ECommerce';
import ProductList from './components/main/ProductList';
import ShoppingCart from './components/main/ShoppingCart';

configure({adapter: new Adapter()});

it('renders without crashing', () => {
  render(
    <Provider store={store}>
      <App />
    </Provider>
  );
});

it('renders ECommerce', () => {
  const wrapper = shallow(<App />);
  expect(wrapper.find(ECommerce).length).toBe(1);
});

it('render ProductList', () => {
  const wrapper = shallow(<ECommerce />);
  expect(wrapper.find(ProductList).length).toBe(1);
});

it('render ShoppingCart', () => {
  const wrapper = shallow(<ECommerce />);
  expect(wrapper.find(ShoppingCart).length).toBe(1);
});